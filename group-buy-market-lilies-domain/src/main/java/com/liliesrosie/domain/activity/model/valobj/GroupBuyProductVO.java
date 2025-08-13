package com.liliesrosie.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 15:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyProductVO {

    /** 商品ID */
    private String goodsId;
    /** 商品名称 */
    private String goodsName;
    /** 原始价格 */
    private BigDecimal originalPrice;
}
