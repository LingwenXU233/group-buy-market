package com.liliesrosie.domain.trade.model.entity;

import com.liliesrosie.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lingwenxu
 * @description 锁单完成后返回
 * @create 2025-08-20 16:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketPayOrderEntity {

    private String orderId;

    private BigDecimal deductPrices;

    private TradeOrderStatusEnumVO tradeOrderStatusEnumVO;
}
