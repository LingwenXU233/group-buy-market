package com.liliesrosie.test.rule02.factory;

import com.liliesrosie.test.rule02.logic.RuleNode201;
import com.liliesrosie.test.rule02.logic.RuleNode202;
import com.liliesrosie.test.rule02.logic.XxxResponse;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedList;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedListBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 16:18
 */
@Slf4j
@Service
public class Rule02TradeRuleFactory {

    @Bean("demo01")
    public BusinessLinkedList<String, DynamicContext, XxxResponse> demo01(RuleNode201 ruleNode201, RuleNode202 ruleNode202) {

        BusinessLinkedListBuilder<String, DynamicContext, XxxResponse> businessLinkedListBuilder = new BusinessLinkedListBuilder<>("demo01", ruleNode201, ruleNode202);

        return businessLinkedListBuilder.getLinkedList();
    }

    @Bean("demo02")
    public BusinessLinkedList<String, DynamicContext, XxxResponse> demo02(RuleNode201 ruleNode201, RuleNode202 ruleNode202) {

        BusinessLinkedListBuilder<String, DynamicContext, XxxResponse> businessLinkedListBuilder = new BusinessLinkedListBuilder<>("demo02",  ruleNode202);

        return businessLinkedListBuilder.getLinkedList();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }
}
