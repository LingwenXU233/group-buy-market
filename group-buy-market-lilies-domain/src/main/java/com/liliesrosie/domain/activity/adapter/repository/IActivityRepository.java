package com.liliesrosie.domain.activity.adapter.repository;

import com.liliesrosie.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyProductVO;
import com.liliesrosie.domain.activity.model.valobj.SCProductActivityVO;
import com.liliesrosie.domain.activity.model.valobj.TeamStatisticVO;

import javax.xml.transform.Source;
import java.util.List;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 14:45
 */
public interface IActivityRepository {
    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    GroupBuyProductVO queryProductByGoodsIdList(String goodsId);

    SCProductActivityVO querySCProductActivityBySCGoodsId(String source, String channel, String goodsId);

    Boolean isTagCrowdRange(String tagId, String userId);

    Boolean downgradeSwitch();

    Boolean cutRange(String userId);

    Boolean whiteList(String tagId);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByOwner(Long activityId, String userId, Integer ownerCount);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByRandom(Long activityId, String userId, Integer randomCount);

    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);

}
