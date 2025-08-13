package com.liliesrosie.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyProductVO;
import com.liliesrosie.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountVOThreadTask;
import com.liliesrosie.domain.activity.service.trial.thread.QueryGroupBuyProductVOFromDBThreadTask;
import com.liliesrosie.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-10 12:33
 */
@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport {

    @Resource
    private EndNode endNode;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;


    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // ascyn search the activity info
        QueryGroupBuyActivityDiscountVOThreadTask queryGroupBuyActivityDiscountVOThreadTask = new QueryGroupBuyActivityDiscountVOThreadTask(requestParameter.getSource(), requestParameter.getChannel(), repository);
        FutureTask<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOFutureTask = new FutureTask<>(queryGroupBuyActivityDiscountVOThreadTask);
        threadPoolExecutor.execute(groupBuyActivityDiscountVOFutureTask);

        // ascyn search for the recommended product for user
        QueryGroupBuyProductVOFromDBThreadTask queryGroupBuyProductVOFromDBThreadTask = new QueryGroupBuyProductVOFromDBThreadTask(requestParameter.getGoodsId(), repository);
        FutureTask<GroupBuyProductVO> queryGroupBuyProductVOFutureTask = new FutureTask<>(queryGroupBuyProductVOFromDBThreadTask);
        threadPoolExecutor.execute(queryGroupBuyProductVOFutureTask);

        // 写入上下文 - 对于一些复杂场景，获取数据的操作，有时候会在下N个节点获取，这样前置查询数据，可以提高接口响应效率
        dynamicContext.setGroupBuyActivityDiscountVO(groupBuyActivityDiscountVOFutureTask.get(timeout, TimeUnit.MINUTES));
        dynamicContext.setGroupBuyProductVO(queryGroupBuyProductVOFutureTask.get(timeout, TimeUnit.MINUTES));

        log.info("拼团商品查询试算服务-MarketNode userId:{} 异步线程加载数据「GroupBuyActivityDiscountVO、GroupBuyProductVO」完成", requestParameter.getUserId());
    }


    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-MarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // todo 拼团优惠试算详细过程

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
