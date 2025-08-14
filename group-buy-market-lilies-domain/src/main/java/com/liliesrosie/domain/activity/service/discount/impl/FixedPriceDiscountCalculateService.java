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
 * @create 2025-08-14 11:26
 */
@Slf4j
@Service("N")
public class FixedPriceDiscountCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", DiscountTypeEnum.get(groupBuyDiscount.getDiscountType()).getInfo());

        // 折扣表达式 - 直接为优惠后的金额
        String marketExpr = groupBuyDiscount.getMarketExpr();

        // N元购
        return new BigDecimal(marketExpr);
    }
}
