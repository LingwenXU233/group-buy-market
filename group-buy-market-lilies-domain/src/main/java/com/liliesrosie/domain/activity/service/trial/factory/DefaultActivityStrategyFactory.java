package com.liliesrosie.domain.activity.service.trial.factory;

import com.liliesrosie.domain.activity.model.entity.MarketProductEntity;
import com.liliesrosie.domain.activity.model.entity.TrialBalanceEntity;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.liliesrosie.domain.activity.model.valobj.GroupBuyProductVO;
import com.liliesrosie.domain.activity.service.trial.node.RootNode;
import com.liliesrosie.types.design.framework.tree.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-10 12:22
 */

@Service
public class DefaultActivityStrategyFactory {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        // 活动折扣信息
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;
        // 商品信息
        private GroupBuyProductVO groupBuyProductVO;
        // 折扣价格
        private BigDecimal deductionPrice;

        // 折扣价格
        private BigDecimal payPrice;

        // 活动可见性
        private Boolean isVisible;

        // 是否参与
        private Boolean isEnable;


    }

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }
}
