package com.liliesrosie.domain.trade.service;

import com.liliesrosie.domain.trade.model.entity.TradePaySettlementEntity;
import com.liliesrosie.domain.trade.model.entity.TradePaySuccessEntity;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-25 14:48
 */
public interface ITradeSettlementOrderService {
    /**
     * 营销结算
     * @param tradePaySuccessEntity 交易支付订单实体对象
     * @return 交易结算订单实体
     */
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception;

}
