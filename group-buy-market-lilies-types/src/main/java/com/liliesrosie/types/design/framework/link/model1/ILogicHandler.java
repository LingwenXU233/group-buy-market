package com.liliesrosie.types.design.framework.link.model1;

/**
 * @author lingwenxu
 * @description 责任链节点接口，扩展了基本节点行为，增加了执行逻辑。
 * @create 2025-08-21 16:05
 */
public interface ILogicHandler<T, D, R> extends ILogicNode<T, D, R> {
    /**
     * 执行当前节点逻辑。
     * @param requestParam     请求参数
     * @param dynamicContext   上下文
     * @return                 处理结果
     * @throws Exception       节点处理异常
     */
    R apply(T requestParam, D dynamicContext) throws Exception;
}
