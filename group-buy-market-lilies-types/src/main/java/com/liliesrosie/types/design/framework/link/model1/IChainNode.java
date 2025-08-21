package com.liliesrosie.types.design.framework.link.model1;

/**
 * @author lingwenxu
 * @description 责任链节点的基本行为接口。
 * @create 2025-08-21 16:05
 */
public interface IChainNode<T, D, R> {
    /**
     * 获取下一个节点
     */
    ILogicHandler<T, D, R> next();
    /**
     * 追加一个下一个节点，并返回追加的节点（便于链式调用）
     */
    ILogicHandler<T, D, R> appendNext(ILogicHandler<T, D, R> next);
}
