package com.liliesrosie.infrastructure.adapter.port;

import com.liliesrosie.domain.trade.adaptor.port.ITradePort;
import com.liliesrosie.domain.trade.model.entity.NotifyTaskEntity;
import com.liliesrosie.infrastructure.gateway.GroupBuyNotifyService;
import com.liliesrosie.infrastructure.redis.IRedisService;
import com.liliesrosie.types.enums.NotifyTaskHTTPEnumVO;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-27 14:19
 */
@Service
public class TradePort implements ITradePort {


    @Resource
    GroupBuyNotifyService groupBuyNotifyService;

    @Resource
    IRedisService redisService;

    @Override
    public String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception {

        RLock lock = redisService.getLock(notifyTask.lockKey());
        // lock.tryLock(...) 可能抛 InterruptedException；
        try{
            if (lock.tryLock(3, 0, TimeUnit.SECONDS)) {
                try {
                    // 无效的 notifyUrl 则直接返回成功
                    if (StringUtils.isBlank(notifyTask.getNotifyUrl()) || "暂无".equals(notifyTask.getNotifyUrl())) {
                        return NotifyTaskHTTPEnumVO.SUCCESS.getCode();
                    }
                    return groupBuyNotifyService.groupBuyNotify(notifyTask.getNotifyUrl(), notifyTask.getParameterJson());
                } finally {
                    if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        }
        catch (Exception e){
            Thread.currentThread().interrupt();
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        }

    }
}
