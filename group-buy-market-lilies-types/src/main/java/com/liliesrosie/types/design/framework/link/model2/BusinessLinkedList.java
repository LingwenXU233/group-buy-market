package com.liliesrosie.types.design.framework.link.model2;

import java.util.LinkedList;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 17:48
 */
public class BusinessLinkedList<T, D, R> extends LinkedList<ILogicHandler<T, D, R>> {

    private final String name;

    public BusinessLinkedList(String name) {
        this.name = name;
    }

    public R execute(T requestParam, D dynamicContext) throws Exception {
        for (ILogicHandler<T, D, R> handler : this) {
            R applyResult = handler.apply(requestParam, dynamicContext);
            if (null != applyResult) return applyResult;
        }
        return null;
    }
}
