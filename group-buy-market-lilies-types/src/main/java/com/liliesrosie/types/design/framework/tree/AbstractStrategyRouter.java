package com.liliesrosie.types.design.framework.tree;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractStrategyRouter<T, D, R> implements StrategyHandler<T, D, R>, StrategyMapper<T, D, R> {

    @Getter
    @Setter
    @SuppressWarnings("unchecked")
    protected StrategyHandler<T, D, R> defaultStrategyHandler = (StrategyHandler<T, D, R>) StrategyHandler.DEFAULT;

    public R router(T requestParameter, D dynamicContext) throws Exception{
        StrategyHandler<T, D, R> next = this.get(requestParameter, dynamicContext);
        if(next != null)return next.apply(requestParameter, dynamicContext);
        else return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }
}
