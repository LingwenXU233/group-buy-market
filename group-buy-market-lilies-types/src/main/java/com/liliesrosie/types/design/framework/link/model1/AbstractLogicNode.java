package com.liliesrosie.types.design.framework.link.model1;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 16:07
 */
public abstract class AbstractLogicNode<T, D, R> implements ILogicHandler<T, D, R> {

    private ILogicHandler<T, D, R> next;

    @Override
    public ILogicHandler<T, D, R> next() {
        return this.next;
    }

    @Override
    public ILogicHandler<T, D, R> appendNext(ILogicHandler<T, D, R> next) {
        this.next = next;
        return next;
    }

    /**
     * 调用下一个节点。
     */
    public R next(T requestParameter, D dynamicContext) throws Exception {
        if (next == null) {
            return null;
        }
        return next.apply(requestParameter, dynamicContext);
    }
}
