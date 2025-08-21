package com.liliesrosie.test.rule01.logic;

import com.liliesrosie.test.rule01.factory.Rule01TradeRuleFactory;
import com.liliesrosie.types.design.framework.link.model1.AbstractLoginLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 16:13
 */
@Slf4j
@Service
public class RuleLogic101 extends AbstractLoginLink<String, Rule01TradeRuleFactory.DynamicContext,String> {

    @Override
    public String apply(String requestParam, Rule01TradeRuleFactory.DynamicContext dynamicContext) throws Exception {

        // 执行逻辑
        log.info("link model01 RuleLogic101");

        // 并调用下一个结点
        return next(requestParam, dynamicContext);
    }
}
