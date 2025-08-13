package com.liliesrosie.domain.activity.service.trial.thread;

import com.liliesrosie.domain.activity.adapter.repository.IActivityRepository;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyProductVO;

import java.util.concurrent.Callable;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 15:34
 */
public class QueryGroupBuyProductVOFromDBThreadTask implements Callable<GroupBuyProductVO> {

    private final String goodsId;

    private final IActivityRepository activityRepository;

    public QueryGroupBuyProductVOFromDBThreadTask(String goodsId, IActivityRepository activityRepository) {
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }


    @Override
    public GroupBuyProductVO call() throws Exception {
        return activityRepository.queryProductByGoodsIdList(goodsId);
    }
}
