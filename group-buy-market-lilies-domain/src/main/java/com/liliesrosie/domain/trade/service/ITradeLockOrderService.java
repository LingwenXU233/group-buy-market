package com.liliesrosie.domain.trade.service;

import com.liliesrosie.domain.trade.model.entity.MarketPayOrderEntity;
import com.liliesrosie.domain.trade.model.entity.PayActivityEntity;
import com.liliesrosie.domain.trade.model.entity.PayDiscountEntity;
import com.liliesrosie.domain.trade.model.entity.UserEntity;
import com.liliesrosie.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-20 14:18
 */
public interface ITradeLockOrderService {

    // 查询有没有 参与拼团，未支付的相关订单
    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    // 查看拼团进度
    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    // 锁单操作
    MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception;
}
