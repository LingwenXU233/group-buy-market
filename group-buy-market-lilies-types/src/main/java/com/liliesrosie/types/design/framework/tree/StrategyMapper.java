package com.liliesrosie.types.design.framework.tree;

/**
 * @author lilies Xu
 * @description strategy mapper to get the next node
 * T request parameter
 * D context
 * R return parameter
 * @create 2025-8-9
 */
public interface StrategyMapper<T, D, R> {

    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;
}
