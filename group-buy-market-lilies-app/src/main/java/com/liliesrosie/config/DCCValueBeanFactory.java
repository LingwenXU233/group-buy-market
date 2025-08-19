package com.liliesrosie.config;

import com.liliesrosie.types.annotations.DCCValue;
import com.liliesrosie.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.lang.model.element.NestingKind;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-19 11:20
 */

@Slf4j
@Configuration
public class DCCValueBeanFactory implements BeanPostProcessor {

    /* redis中的key的拼接 */
    private static final String BASE_CONFIG_PATH = "group_buy_market_dcc_";

    private final RedissonClient redissonClient;

    private final Map<String, Object> dccObjGroup = new HashMap<>();


    public DCCValueBeanFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // 获取Class对象和bean对象
        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;

        if (AopUtils.isAopProxy(bean)) {
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean);
        }

        //
        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field: fields) {
            if(!field.isAnnotationPresent(DCCValue.class)){
                continue;
            }

            // 获取注解中传入的值
            DCCValue dccValue = field.getAnnotation(DCCValue.class);
            String value = dccValue.value();

            if (StringUtils.isBlank(value)) {
                throw new RuntimeException(field.getName() + " @DCCValue is not config value config case 「isSwitch/isSwitch:1」");
            }
            String[] splits = value.split(":");
            String key = BASE_CONFIG_PATH.concat(splits[0]); // key = group_buy_market_dcc_cutRange
            String defaultValue = splits.length == 2 ? splits[1] : null;

            // 设置值
            String setValue = defaultValue;

            // 如果为空则抛出异常
            if (StringUtils.isBlank(defaultValue)) {
                throw new RuntimeException("dcc config error " + key + " is not null - 请配置默认值！");
            }

            try{
                // Redis 操作，判断配置Key是否存在，不存在则创建，存在则获取最新值
                RBucket<String> bucket = redissonClient.getBucket(key);
                boolean exists = bucket.isExists();
                if(!exists){
                    bucket.set(setValue);
                }else{
                    setValue = bucket.get();
                }

                // 填充回本地的bean中
                field.setAccessible(true);
                field.set(targetBeanObject, setValue);
                field.setAccessible(false);

            }catch (Exception e){
                throw new RuntimeException(e);
            }

            dccObjGroup.put(key, targetBeanObject);
        }

        return bean;
    }

    /**
     * 将channel注册为bean，以及channel得到信息时应对策略
     */
    @Bean("dccTopic")
    public RTopic dccRedisTopicListener(RedissonClient redissonClient){

        // 获取一个channel用于监听
        RTopic topic = redissonClient.getTopic("group_buy_market_dcc");

        topic.addListener(String.class, (charSequence, s)->{

            String[] split = s.split(Constants.SPLIT, 2);

            // 获取值
            String attribute = split[0];
            String key = BASE_CONFIG_PATH + attribute;
            String value  = split[1];

            // 设置值
            RBucket<String> bucket = redissonClient.getBucket(key);
            boolean exists = bucket.isExists();
            if (!exists) return;
            bucket.set(value);

            Object objBean = dccObjGroup.get(key);
            if (objBean == null) return;

            //
            Class<?> objBeanClass = objBean.getClass();
            // 检查 objBean 是否是代理对象
            if (AopUtils.isAopProxy(objBean)) {
                // 获取代理对象的目标对象
                objBeanClass = AopUtils.getTargetClass(objBean);
            }

            try{
                Field field = objBeanClass.getDeclaredField(attribute);
                field.setAccessible(true);
                field.set(objBean, value);
                field.setAccessible(false);

                log.info("DCC 节点监听，动态设置值 {} {}", key, value);

            }catch (Exception e){
                throw new RuntimeException(e);
            }

        });
        return topic;
    }
}
