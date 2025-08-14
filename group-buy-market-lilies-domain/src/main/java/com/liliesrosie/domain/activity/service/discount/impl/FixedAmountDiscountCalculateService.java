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
 * @create 2025-08-14 11:31
 */
@Slf4j
@Service("ZJ")
public class FixedAmountDiscountCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", DiscountTypeEnum.get(groupBuyDiscount.getDiscountType()).getInfo());

        // 折扣表达式 - 直接为优惠后的金额
        String marketExpr = groupBuyDiscount.getMarketExpr();

        // 折扣价格
        BigDecimal deductionPrice = originalPrice.subtract(new BigDecimal(marketExpr));

        // 判断折扣后金额，最低支付1分钱
        if(deductionPrice.compareTo(BigDecimal.ZERO)<= 0){
            return new BigDecimal("0.01");
        }

        return deductionPrice;


    }
}
