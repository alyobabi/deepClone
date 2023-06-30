package org.example.strategy;

import org.example.exception.DeepCopyException;

public interface DeepCopyStrategy<T> {
    T deepCopy(T obj) throws DeepCopyException;
}