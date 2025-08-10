package com.liliesrosie.domain.activity.service.trial.node;

import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.types.design.framework.tree.AbstractStrategyRouter;
import com.liliesrosie.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lingwenxu
 * @description rootnode
 * @create 2025-08-10 12:18
 */
@Slf4j
@Service
public class RootNode extends AbstractGroupBuyMarketSupport {


    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }
}
