package com.liliesrosie.domain.activity.service.discount;

import com.liliesrosie.domain.activity.adapter.repository.IActivityRepository;
import com.liliesrosie.domain.activity.model.valobj.DiscountTypeEnum;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.service.discount.IDiscountCalculationService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-13 19:05
 */
public abstract class AbstractDiscountCalculateService implements IDiscountCalculationService {

    @Resource
    protected IActivityRepository repository;
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {

        // 1. 人群标签过滤
        if (DiscountTypeEnum.TAG == DiscountTypeEnum.get(groupBuyDiscount.getDiscountType())){
            boolean isCrowdRange = filterTagId(userId, groupBuyDiscount.getTagId());
            if (!isCrowdRange) return originalPrice;
        }
        // 2. 折扣优惠计算
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    // 人群过滤 - 限定人群优惠
    private boolean filterTagId(String userId, String tagId) {
        return repository.isTagCrowdRange(tagId, userId);
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}
