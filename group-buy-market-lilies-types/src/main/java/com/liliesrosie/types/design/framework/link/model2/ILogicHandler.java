package com.liliesrosie.types.design.framework.link.model2;

/**
 * @author lingwenxu
 * @description  单个责任链处理器接口
 * @create 2025-08-21 17:47
 */
public interface ILogicHandler<T, D, R> {
    R apply(T requestParam, D dynamicContext) throws Exception;
}
