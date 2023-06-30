package org.example.strategy.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import java.util.TreeSet;
import org.example.CopyUtils;
import org.example.exception.DeepCopyException;
import org.example.strategy.DeepCopyStrategy;

public class CollectionDeepCopyStrategy<T> implements DeepCopyStrategy<Collection<T>> {
    @Override
    public Collection<T> deepCopy(Collection<T> obj) throws DeepCopyException {
        String classSimpleName = obj.getClass().getSimpleName();

        Collection<T> copiedCollection = switch (classSimpleName) {
            case "ArrayList" -> new ArrayList<>();
            case "LinkedList" -> new LinkedList<>();
            case "HashSet" -> new HashSet<>();
            case "TreeSet" -> new TreeSet<>();
            case "LinkedHashSet" -> new LinkedHashSet<>();
            default -> throw new UnsupportedOperationException();
        };
        for (T element : obj) {
            copiedCollection.add(CopyUtils.deepCopy(element));
        }
        return copiedCollection;
    }
}
