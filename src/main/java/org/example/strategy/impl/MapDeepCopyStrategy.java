package org.example.strategy.impl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import org.example.CopyUtils;
import org.example.exception.DeepCopyException;
import org.example.strategy.DeepCopyStrategy;

public class MapDeepCopyStrategy<K, V> implements DeepCopyStrategy<Map<K, V>> {
    @Override
    public Map<K, V> deepCopy(Map<K, V> obj) throws DeepCopyException {
        String classSimpleName = obj.getClass().getSimpleName();

        Map<K, V> copiedMap = switch (classSimpleName) {
            case "HashMap" -> new HashMap<>();
            case "Hashtable" -> new Hashtable<>();
            case "LinkedHashMap" -> new LinkedHashMap<>();
            case "TreeMap" -> new TreeMap<>();
            default -> throw new UnsupportedOperationException();
        };

        for(Map.Entry<K, V> entry : obj.entrySet()) {
            K key = CopyUtils.deepCopy(entry.getKey());
            V value = CopyUtils.deepCopy(entry.getValue());
            copiedMap.put(key, value);
        }

        return copiedMap;
    }
}
