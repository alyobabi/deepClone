package org.example.strategy.impl;

import java.util.Arrays;
import org.example.CopyUtils;
import org.example.exception.DeepCopyException;
import org.example.strategy.DeepCopyStrategy;

public class ArrayDeepCopyStrategy<T> implements DeepCopyStrategy<T[]> {
    @Override
    public T[] deepCopy(T[] obj) throws DeepCopyException {
        T[] copiedArray = Arrays.copyOf(obj, obj.length);
        for (int i = 0; i < obj.length; i++) {
            copiedArray[i] = CopyUtils.deepCopy(obj[i]);
        }
        return copiedArray;
    }
}