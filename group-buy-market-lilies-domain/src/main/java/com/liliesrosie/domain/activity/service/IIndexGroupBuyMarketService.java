package com.liliesrosie.domain.activity.service;

import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;

/**
 * @author lingwenxu
 * @description index page group buy market trail calculating interface
 * @create 2025-08-10 12:38
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

}
