package com.liliesrosie.types.design.framework.link.model2;

import java.util.Arrays;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-21 17:53
 */
public class BusinessLinkedListBuilder<T, D, R> {
    private final BusinessLinkedList<T, D, R> businessLinkedList;


    @SafeVarargs
    public BusinessLinkedListBuilder(String name, ILogicHandler<T, D, R>... handlers) {
        this.businessLinkedList = new BusinessLinkedList<>(name);
        this.businessLinkedList.addAll(Arrays.asList(handlers));
    }

    public BusinessLinkedList<T, D, R> getLinkedList() {
        return this.businessLinkedList;
    }
}
