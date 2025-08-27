package com.liliesrosie.domain.trade.service.settlement.factory;

import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.domain.trade.model.entity.GroupBuyTeamEntity;
import com.liliesrosie.domain.trade.model.entity.MarketPayOrderEntity;
import com.liliesrosie.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import com.liliesrosie.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import com.liliesrosie.domain.trade.service.settlement.filter.EndRuleFilter;
import com.liliesrosie.domain.trade.service.settlement.filter.OutTradeNoRuleFilter;
import com.liliesrosie.domain.trade.service.settlement.filter.SCRuleFilter;
import com.liliesrosie.domain.trade.service.settlement.filter.SettableRuleFilter;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedList;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedListBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-26 10:52
 */
@Slf4j
@Service
public class TradeSettlementRuleFilterFactory {

    @Bean("tradeSettlementRuleFilterChain")
    public BusinessLinkedList<TradeSettlementRuleCommandEntity, DynamicContext, TradeSettlementRuleFilterBackEntity> get(SCRuleFilter scRuleFilter, OutTradeNoRuleFilter outTradeNoRuleFilter, SettableRuleFilter settableRuleFilter, EndRuleFilter endRuleFilter){

        return new BusinessLinkedListBuilder<>("TradeSettlementRuleFilterChain", scRuleFilter, outTradeNoRuleFilter, settableRuleFilter, endRuleFilter).getLinkedList();

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext{
        // 订单营销实体对象
        private MarketPayOrderEntity marketPayOrderEntity;

        // 拼团组队实体对象
        private GroupBuyTeamEntity groupBuyTeamEntity;
    }
}
