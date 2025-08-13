package com.liliesrosie.domain.activity.service.trial;

import com.liliesrosie.domain.activity.adapter.repository.IActivityRepository;
import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.types.design.framework.tree.AbstractStrategyRouter;
import com.liliesrosie.types.design.framework.tree.AbstrctMultiThreadStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-10 12:20
 */
public abstract class AbstractGroupBuyMarketSupport extends AbstrctMultiThreadStrategyRouter<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    protected long timeout = 500;
    @Resource
    protected IActivityRepository repository;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

    }

}
