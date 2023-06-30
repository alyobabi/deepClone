package org.example.strategy.impl;

import org.example.strategy.DeepCopyStrategy;

public class ImmutableValueDeepCopyStrategy<T> implements DeepCopyStrategy<T> {
    @Override
    public T deepCopy(T obj) {
        return obj;
    }
}
