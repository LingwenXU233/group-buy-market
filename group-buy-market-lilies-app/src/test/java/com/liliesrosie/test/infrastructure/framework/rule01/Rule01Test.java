package com.liliesrosie.test.infrastructure.framework.rule01;

import com.alibaba.fastjson.JSON;
import com.liliesrosie.test.infrastructure.framework.rule01.factory.Rule01TradeRuleFactory;
import com.liliesrosie.types.design.framework.link.model1.ILogicHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 16:40
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Rule01Test {

    @Resource
    Rule01TradeRuleFactory rule01TradeRuleFactory;

    @Test
    public void testRule1Chain() throws Exception {
        ILogicHandler<String, Rule01TradeRuleFactory.DynamicContext, String> logicLink = rule01TradeRuleFactory.openLogicLink();
        String logic = logicLink.apply("123", new Rule01TradeRuleFactory.DynamicContext());
        log.info("测试结果:{}", JSON.toJSONString(logic));
    }
}
