package com.liliesrosie.infrastructure.adapter.repository;

import com.liliesrosie.domain.activity.adapter.repository.IActivityRepository;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyProductVO;
import com.liliesrosie.domain.activity.model.valobj.SCProductActivityVO;
import com.liliesrosie.infrastructure.dao.IGroupBuyActivityDao;
import com.liliesrosie.infrastructure.dao.IGroupBuyDiscountDao;
import com.liliesrosie.infrastructure.dao.IGroupBuyProductDao;
import com.liliesrosie.infrastructure.dao.ISCProductActivityDao;
import com.liliesrosie.infrastructure.dao.po.GroupBuyActivity;
import com.liliesrosie.infrastructure.dao.po.GroupBuyDiscount;
import com.liliesrosie.infrastructure.dao.po.GroupBuyProduct;
import com.liliesrosie.infrastructure.dao.po.SCProductActivity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-13 14:50
 */
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;

    @Resource
    private IGroupBuyProductDao groupBuyProductDao;

    @Resource
    private ISCProductActivityDao scProductActivityDao;

    public SCProductActivityVO querySCProductActivityBySCGoodsId(String source, String channel, String goodsId){
        SCProductActivity scProductActivityReq = new SCProductActivity();
        scProductActivityReq.setChannel(channel);
        scProductActivityReq.setSource(source);
        scProductActivityReq.setGoodsId(goodsId);
        SCProductActivity scProductActivity = scProductActivityDao.querySCProductActivity(scProductActivityReq);

        if (scProductActivity == null) return null;

        return SCProductActivityVO.builder()
                .source(scProductActivity.getSource())
                .channel(scProductActivity.getChannel())
                .activityId(scProductActivity.getActivityId())
                .goodsId(scProductActivity.getGoodsId())
                .build();
    }

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {


        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityById(activityId);
        if (null == groupBuyActivityRes) return null;

        String discountId = groupBuyActivityRes.getDiscountId();

        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(discountId);
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(groupBuyDiscountRes.getDiscountType())
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .tagId(groupBuyDiscountRes.getTagId())
                .build();

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .groupBuyDiscount(groupBuyDiscount)
                .groupType(groupBuyActivityRes.getGroupType())
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .validTime(groupBuyActivityRes.getValidTime())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .tagId(groupBuyActivityRes.getTagId())
                .tagScope(groupBuyActivityRes.getTagScope())
                .build();
    }

    @Override
    public GroupBuyProductVO queryProductByGoodsIdList(String goodsId) {
        GroupBuyProduct sku = groupBuyProductDao.queryProductByGoodsIdList(goodsId);
        if(sku == null) return null;
        return GroupBuyProductVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }
}
