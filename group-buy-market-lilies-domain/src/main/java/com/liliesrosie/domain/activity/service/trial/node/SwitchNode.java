package com.liliesrosie.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.types.design.framework.tree.StrategyHandler;
import com.liliesrosie.types.enums.ResponseCode;
import com.liliesrosie.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-10 12:32
 */
@Slf4j
@Service
public class SwitchNode extends AbstractGroupBuyMarketSupport {
    @Resource
    private MarketNode marketNode;


    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-SwitchNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // 根据用户ID切量
        String userId = requestParameter.getUserId();

        // 判断是否降级
        if(repository.downgradeSwitch()){
            log.info("拼团活动降级拦截 {}", userId);
            throw new AppException(ResponseCode.E003.getCode(), ResponseCode.E003.getInfo());
        }

        if(repository.cutRange(userId)){
            log.info("拼团活动用户切量 {}", userId);
            throw new AppException(ResponseCode.E004.getCode(), ResponseCode.E004.getInfo());
        }

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

        // 不满足情况下
        // 满足条件的情况下
        return marketNode;
    }
}
