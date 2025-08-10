package com.liliesrosie.domain.activity.service.trial;

import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.types.design.framework.tree.AbstractStrategyRouter;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-10 12:20
 */
public abstract class AbstractGroupBuyMarketSupport extends AbstractStrategyRouter<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

}
