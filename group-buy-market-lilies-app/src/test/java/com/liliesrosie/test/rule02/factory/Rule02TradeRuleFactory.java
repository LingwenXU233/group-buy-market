package com.liliesrosie.test.rule02.factory;

import com.liliesrosie.test.rule02.logic.RuleLogic201;
import com.liliesrosie.test.rule02.logic.RuleLogic202;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedList;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedListBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Resource
    @Qualifier("RuleLogic201")
    RuleLogic201 ruleLogic201;

    @Resource
    @Qualifier("RuleLogic202")
    RuleLogic202 ruleLogic202;

    public BusinessLinkedList<String, Rule02TradeRuleFactory.DynamicContext, String> openLogicLink() throws Exception {
        BusinessLinkedListBuilder<String, DynamicContext, String> businessLinkedListBuilder = new BusinessLinkedListBuilder<>("model02", ruleLogic201, ruleLogic202);
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
