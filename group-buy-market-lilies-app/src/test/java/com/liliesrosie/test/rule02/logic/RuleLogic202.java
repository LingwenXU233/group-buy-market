package com.liliesrosie.test.rule02.logic;

import com.liliesrosie.test.rule02.factory.Rule02TradeRuleFactory;
import com.liliesrosie.types.design.framework.link.model2.ILogicHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 18:03
 */
@Slf4j
@Service("RuleLogic202")
public class RuleLogic202 implements ILogicHandler<String, Rule02TradeRuleFactory.DynamicContext, String > {

    @Override
    public String apply(String requestParam, Rule02TradeRuleFactory.DynamicContext dynamicContext) throws Exception {
        log.info("link model02 RuleLogic202");
        return null;
    }
}
