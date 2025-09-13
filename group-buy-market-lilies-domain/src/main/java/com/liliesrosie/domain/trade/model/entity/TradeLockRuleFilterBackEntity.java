package com.liliesrosie.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-22 14:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeLockRuleFilterBackEntity {
    // 用户参与活动的订单量
    private Integer userTakeOrderCount;

    private String recoveryTeamSlotKey;
}
