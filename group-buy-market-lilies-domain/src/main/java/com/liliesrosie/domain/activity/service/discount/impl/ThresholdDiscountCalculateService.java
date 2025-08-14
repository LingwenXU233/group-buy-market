package com.liliesrosie.domain.activity.service.discount.impl;

import com.liliesrosie.domain.activity.model.valobj.DiscountTypeEnum;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.service.discount.AbstractDiscountCalculateService;
import com.liliesrosie.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lingwenxu
 * @description 满减优惠Threshold Discount Calculation: X - Y (100 - 10)
 * @create 2025-08-14 11:03
 */
@Slf4j
@Service("MJ")
public class ThresholdDiscountCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", DiscountTypeEnum.get(groupBuyDiscount.getDiscountType()).getInfo());

        // discount expression - X, Y 满X减Y元
        String marketExpr = groupBuyDiscount.getMarketExpr();
        String[] split = marketExpr.split(Constants.SPLIT);
        BigDecimal x = new BigDecimal(split[0].trim());
        BigDecimal y = new BigDecimal(split[1].trim());

        // 不满足最低满减约束，则按照原价
        if (originalPrice.compareTo(x) < 0) {
            return originalPrice;
        }

        // 折扣价格
        BigDecimal deductionPrice = originalPrice.subtract(y);

        // 判断折扣后金额，最低支付1分钱
        if (deductionPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }

        return deductionPrice;
    }
}
