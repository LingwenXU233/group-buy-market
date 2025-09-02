package com.liliesrosie.infrastructure.adapter.port;

import com.liliesrosie.domain.trade.adaptor.port.ITradePort;
import com.liliesrosie.domain.trade.model.entity.NotifyTaskEntity;
import com.liliesrosie.domain.trade.model.valobj.NotifyTypeEnumVO;
import com.liliesrosie.infrastructure.event.EventPublisher;
import com.liliesrosie.infrastructure.gateway.GroupBuyNotifyService;
import com.liliesrosie.infrastructure.redis.IRedisService;
import com.liliesrosie.types.enums.NotifyTaskHTTPEnumVO;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class TradePort implements ITradePort {


    @Resource
    GroupBuyNotifyService groupBuyNotifyService;

    @Resource
    IRedisService redisService;

    @Resource
    private EventPublisher publisher;

    @Override
    public String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception {

        RLock lock = redisService.getLock(notifyTask.lockKey());
        // lock.tryLock(...) 可能抛 InterruptedException；
        try{
            if (lock.tryLock(3, 0, TimeUnit.SECONDS)) {
                try {
                    // 回调http
                    if(NotifyTypeEnumVO.HTTP.getCode().equals(notifyTask.getNotifyType())){
                        if (StringUtils.isBlank(notifyTask.getNotifyUrl()) || "暂无".equals(notifyTask.getNotifyUrl())) {
                            return NotifyTaskHTTPEnumVO.SUCCESS.getCode();
                        }

                        return groupBuyNotifyService.groupBuyNotify(notifyTask.getNotifyUrl(), notifyTask.getParameterJson());
                    }
                    // 回调mq
                    if(NotifyTypeEnumVO.MQ.getCode().equals(notifyTask.getNotifyType())){
                        log.info("开始拼团回调，消息为{}，routing_key为{}", notifyTask.getParameterJson(), notifyTask.getNotifyMq());
                        publisher.publish(notifyTask.getNotifyMq(), notifyTask.getParameterJson());
                        NotifyTaskHTTPEnumVO.SUCCESS.getCode();
                    }

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
