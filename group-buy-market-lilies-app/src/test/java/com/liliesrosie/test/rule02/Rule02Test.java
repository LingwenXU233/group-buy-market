package com.liliesrosie.test.rule02;

import com.alibaba.fastjson.JSON;
import com.liliesrosie.test.rule01.factory.Rule01TradeRuleFactory;
import com.liliesrosie.test.rule02.factory.Rule02TradeRuleFactory;
import com.liliesrosie.types.design.framework.link.model1.ILogicHandler;
import com.liliesrosie.types.design.framework.link.model2.BusinessLinkedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 18:09
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Rule02Test {
    @Resource
    Rule02TradeRuleFactory rule02TradeRuleFactory;

    @Test
    public void testRule2Chain() throws Exception {
        BusinessLinkedList<String, Rule02TradeRuleFactory.DynamicContext, String> logicLink = rule02TradeRuleFactory.openLogicLink();
        String logic = logicLink.apply("123", new Rule02TradeRuleFactory.DynamicContext());
        log.info("测试结果:{}", JSON.toJSONString(logic));
    }
}
