package com.liliesrosie.test.rule01.logic;

import com.liliesrosie.test.rule01.factory.Rule01TradeRuleFactory;
import com.liliesrosie.types.design.framework.link.model1.AbstractLoginLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 16:33
 */
@Slf4j
@Service
public class RuleLogic202 extends AbstractLoginLink<String, Rule01TradeRuleFactory.DynamicContext, String > {

    @Override
    public String apply(String requestParam, Rule01TradeRuleFactory.DynamicContext dynamicContext) throws Exception {
        log.info("link model01 RuleLogic202");
        return "link model01 单实例链";
    }
}
