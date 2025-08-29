package com.liliesrosie.test.infrastructure.framework.rule01.factory;

import com.liliesrosie.test.infrastructure.framework.rule01.logic.RuleNode101;
import com.liliesrosie.test.infrastructure.framework.rule01.logic.RuleNode102;
import com.liliesrosie.types.design.framework.link.model1.ILogicHandler;
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
public class Rule01TradeRuleFactory {
    @Resource
    @Qualifier("RuleNode101")
    RuleNode101 ruleNode101;

    @Resource
    @Qualifier("RuleNode102")
    RuleNode102 ruleNode102;


    /**
     * 装配规则链，返回第一个结点
     * @return
     */
    public ILogicHandler<String, DynamicContext, String> openLogicLink(){
        log.info("装配规则链");
        ruleNode101.appendNext(ruleNode102);
        return ruleNode101;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }
}
