package com.liliesrosie.test.infrastructure.framework.rule02.logic;

import com.liliesrosie.test.infrastructure.framework.rule02.factory.Rule02TradeRuleFactory;
import com.liliesrosie.types.design.framework.link.model2.ILogicHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 18:01
 */
@Slf4j
@Service("RuleNode201")
public class RuleNode201 implements ILogicHandler<String, Rule02TradeRuleFactory.DynamicContext, XxxResponse > {

    @Override
    public XxxResponse apply(String requestParam, Rule02TradeRuleFactory.DynamicContext dynamicContext) throws Exception {
        log.info("link model02 RuleLogic101");
        return null;
    }
}
