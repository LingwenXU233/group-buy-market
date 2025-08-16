package com.liliesrosie.types.design.framework.tree;

import java.security.Key;

public interface StrategyHandler<T, D, R> {

    StrategyHandler<?, ?, ?> DEFAULT = (req, ctx) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;
}
