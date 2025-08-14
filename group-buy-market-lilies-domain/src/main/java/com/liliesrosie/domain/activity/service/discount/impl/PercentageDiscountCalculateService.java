package com.liliesrosie.domain.activity.service.discount.impl;

import com.liliesrosie.domain.activity.model.valobj.DiscountTypeEnum;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 11:35
 */
@Slf4j
@Service("ZK")
public class PercentageDiscountCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", DiscountTypeEnum.get(groupBuyDiscount.getDiscountType()).getInfo());

        String marketExpr = groupBuyDiscount.getMarketExpr();

        // 折扣 百分比
        BigDecimal percent = new BigDecimal(marketExpr);
        BigDecimal deductionPrice = originalPrice.multiply(percent);

        // 判断折扣后金额，最低支付1分钱
        if (deductionPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }

        return deductionPrice;
    }
}
