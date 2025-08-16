package com.liliesrosie.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-16 12:42
 */
@Slf4j
@Service
public class TagNode extends AbstractGroupBuyMarketSupport {

    @Resource
    EndNode endNode;
    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-TagNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // get the default cxt info
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        Boolean isVisible = groupBuyActivityDiscountVO.isVisible();
        Boolean isEnable = groupBuyActivityDiscountVO.isEnable();

        String activityTagId = groupBuyActivityDiscountVO.getTagId();

        // 人群标签配置为空，则走默认值
        if(StringUtils.isBlank(activityTagId)){
            dynamicContext.setIsVisible(isVisible);
            dynamicContext.setIsEnable(isEnable);
            return router(requestParameter, dynamicContext);
        }

        // 人群标签不为空
        Boolean isWithin = repository.isTagCrowdRange(activityTagId, requestParameter.getUserId());
        dynamicContext.setIsVisible(isVisible || isWithin);
        dynamicContext.setIsEnable(isEnable || isWithin);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
