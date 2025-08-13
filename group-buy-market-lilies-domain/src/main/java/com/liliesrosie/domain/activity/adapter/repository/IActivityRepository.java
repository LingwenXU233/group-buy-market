package com.liliesrosie.domain.activity.adapter.repository;

import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyProductVO;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 14:45
 */
public interface IActivityRepository {
    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    GroupBuyProductVO queryProductByGoodsIdList(String goodsId);
}
