package com.liliesrosie.domain.trade.model.aggregate;

import com.liliesrosie.domain.trade.model.entity.GroupBuyTeamEntity;
import com.liliesrosie.domain.trade.model.entity.TradePaySuccessEntity;
import com.liliesrosie.domain.trade.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-25 15:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyTeamSettlementAggregate {

    /** 用户实体对象 */
    private UserEntity userEntity;

    /** 拼团组队实体对象 */
    private GroupBuyTeamEntity groupBuyTeamEntity;

    /** 交易支付订单实体对象 */
    private TradePaySuccessEntity tradePaySuccessEntity;
}
