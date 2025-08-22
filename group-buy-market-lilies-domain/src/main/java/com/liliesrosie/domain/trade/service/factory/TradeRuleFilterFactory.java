package com.liliesrosie.domain.trade.service.factory;

import com.liliesrosie.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.liliesrosie.domain.trade.model.entity.GroupBuyActivityEntity;
import com.liliesrosie.domain.trade.model.entity.TradeRuleCommandEntity;
import com.liliesrosie.domain.trade.model.entity.TradeRuleFilterBackEntity;
import com.liliesrosie.domain.trade.service.filter.ActivityUsibilityRuleFilter;
import com.liliesrosie.domain.trade.service.filter.UserTakeLimitRuleFilter;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedList;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedListBuilder;
import com.liliesrosie.types.enums.ActivityStatusEnumVO;
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
 * @create 2025-08-22 14:27
 */
@Slf4j
@Service
public class TradeRuleFilterFactory {

    @Bean("tradeRuleFilterChain")
    BusinessLinkedList<TradeRuleCommandEntity, DynamicContext, TradeRuleFilterBackEntity> tradeRuleFilterChain(ActivityUsibilityRuleFilter activityUsibilityRuleFilter, UserTakeLimitRuleFilter userTakeLimitRuleFilter){

        BusinessLinkedListBuilder<TradeRuleCommandEntity, DynamicContext, TradeRuleFilterBackEntity> businessLinkedListBuilder = new BusinessLinkedListBuilder<>("交易规则过滤链", activityUsibilityRuleFilter, userTakeLimitRuleFilter);
        return businessLinkedListBuilder.getLinkedList();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext{
        private GroupBuyActivityEntity groupBuyActivity;
    }

}
