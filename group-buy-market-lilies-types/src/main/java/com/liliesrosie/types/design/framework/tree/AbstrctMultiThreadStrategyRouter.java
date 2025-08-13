package com.liliesrosie.types.design.framework.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-11 14:21
 */
public abstract class AbstrctMultiThreadStrategyRouter<T, D, R> implements StrategyMapper<T, D, R>, StrategyHandler<T, D, R>{

    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    public R router(T requestParameter, D dynamicContext) throws Exception{
        StrategyHandler<T, D, R> next = this.get(requestParameter, dynamicContext);
        if(next != null)return next.apply(requestParameter, dynamicContext);
        else return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }

    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        multiThread(requestParameter, dynamicContext);
        return doApply(requestParameter, dynamicContext);
    }

    protected abstract R doApply(T requestParameter, D dynamicContext) throws Exception;
    protected abstract void multiThread(T requestParameter, D dynamicContext) throws Exception;


}
