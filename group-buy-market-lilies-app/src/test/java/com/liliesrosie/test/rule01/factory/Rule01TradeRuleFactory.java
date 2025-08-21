package com.liliesrosie.test.rule01.factory;

import com.liliesrosie.test.rule01.logic.RuleLogic101;
import com.liliesrosie.test.rule01.logic.RuleLogic202;
import com.liliesrosie.types.design.framework.link.model1.ILogicHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    RuleLogic101 ruleLogic101;

    @Resource
    RuleLogic202 ruleLogic202;


    /**
     * 装配规则链，返回第一个结点
     * @return
     */
    public ILogicHandler<String, DynamicContext, String> openLogicLink(){
        log.info("装配规则链");
        ruleLogic101.appendNext(ruleLogic202);
        return ruleLogic101;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }
}
