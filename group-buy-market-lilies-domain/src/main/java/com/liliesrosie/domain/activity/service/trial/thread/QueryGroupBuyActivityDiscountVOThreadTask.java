package com.liliesrosie.domain.activity.service.trial.thread;

import com.liliesrosie.domain.activity.adapter.repository.IActivityRepository;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyProductVO;
import com.liliesrosie.domain.activity.model.valobj.SCProductActivityVO;

import java.util.concurrent.Callable;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 15:28
 */
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {

    /**
     * 来源
     */
    private final String source;

    /**
     * 渠道
     */
    private final String channel;

    /**
     * 商品Id
     */
    private final String goodsId;

    /**
     * 活动仓储
     */
    private final IActivityRepository activityRepository;

    public QueryGroupBuyActivityDiscountVOThreadTask(String source, String channel, String goodsId, IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {
        // 通过商品信息查询 配置的相关活动id
        SCProductActivityVO scProductActivityVO = activityRepository.querySCProductActivityBySCGoodsId(source, channel, goodsId);
        if(scProductActivityVO == null) return null;

        // 通过活动id得到具体活动信息
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO= activityRepository.queryGroupBuyActivityDiscountVO(scProductActivityVO.getActivityId());
        return groupBuyActivityDiscountVO;
    }
}
