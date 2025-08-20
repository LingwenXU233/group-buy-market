package com.liliesrosie.domain.trade.adaptor.repository;

import com.liliesrosie.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.liliesrosie.domain.trade.model.entity.MarketPayOrderEntity;
import com.liliesrosie.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-20 14:16
 */
public interface ITradeRepository {

    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);
}
