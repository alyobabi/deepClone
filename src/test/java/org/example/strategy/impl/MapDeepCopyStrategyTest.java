package org.example.strategy.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class MapDeepCopyStrategyTest {

    private MapDeepCopyStrategy<Integer, String> strategy;

    @BeforeEach
    public void setUp() {
        strategy = new MapDeepCopyStrategy<>();
    }

    @Test
    public void shouldDeepCopyHashMap() {
        HashMap<Integer, String> originalMap = new HashMap<>();
        fillMap(originalMap);

        Map<Integer, String> copiedMap = strategy.deepCopy(originalMap);

        assertNotSame(originalMap, copiedMap);
        assertEquals(originalMap, copiedMap);
    }

    @Test
    public void shouldDeepCopyHashtable() {
        Hashtable<Integer, String> originalMap = new Hashtable<>();
        fillMap(originalMap);


        Map<Integer, String> copiedMap = strategy.deepCopy(originalMap);

        assertNotSame(originalMap, copiedMap);
        assertEquals(originalMap, copiedMap);
    }

    @Test
    public void shouldDeepCopyLinkedHashMap() {
        LinkedHashMap<Integer, String> originalMap = new LinkedHashMap<>();
        fillMap(originalMap);


        Map<Integer, String> copiedMap = strategy.deepCopy(originalMap);

        assertNotSame(originalMap, copiedMap);
        assertEquals(originalMap, copiedMap);
    }

    @Test
    public void shouldDeepCopyTreeMap() {
        TreeMap<Integer, String> originalMap = new TreeMap<>();
        fillMap(originalMap);


        Map<Integer, String> copiedMap = strategy.deepCopy(originalMap);

        assertNotSame(originalMap, copiedMap);
        assertEquals(originalMap, copiedMap);
    }

    private void fillMap(Map<Integer, String> originalMap) {
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");
    }
}
