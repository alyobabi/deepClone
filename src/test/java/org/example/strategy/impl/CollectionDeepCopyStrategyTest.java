package org.example.strategy.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CollectionDeepCopyStrategyTest {

    private static final int INT_1 = 1;
    private static final int INT_2 = 2;
    private static final int INT_3 = 3;

    private CollectionDeepCopyStrategy<Integer> strategy;

    @BeforeEach
    public void setUp() {
        strategy = new CollectionDeepCopyStrategy<>();
    }

    @Test
    public void shouldDeepCopyArrayList() {
        ArrayList<Integer> originalList = new ArrayList<>();
        fillCollectionWithIntegers(originalList);

        Collection<Integer> copiedList = strategy.deepCopy(originalList);

        assertNotSame(originalList, copiedList);
        assertEquals(originalList, copiedList);
    }

    @Test
    public void shouldDeepCopyLinkedList() {
        LinkedList<Integer> originalList = new LinkedList<>();
        fillCollectionWithIntegers(originalList);

        Collection<Integer> copiedList = strategy.deepCopy(originalList);

        assertNotSame(originalList, copiedList);
        assertEquals(originalList, copiedList);
    }

    @Test
    public void shouldDeepCopyHashSet() {
        HashSet<Integer> originalSet = new HashSet<>();
        fillCollectionWithIntegers(originalSet);

        Collection<Integer> copiedSet = strategy.deepCopy(originalSet);

        assertNotSame(originalSet, copiedSet);
        assertEquals(originalSet, copiedSet);
    }

    @Test
    public void shouldDeepCopyTreeSet() {
        TreeSet<Integer> originalSet = new TreeSet<>();
        fillCollectionWithIntegers(originalSet);

        Collection<Integer> copiedSet = strategy.deepCopy(originalSet);

        assertNotSame(originalSet, copiedSet);
        assertEquals(originalSet, copiedSet);
    }

    @Test
    public void shouldDeepCopyLinkedHashSet() {
        LinkedHashSet<Integer> originalSet = new LinkedHashSet<>();
        fillCollectionWithIntegers(originalSet);

        Collection<Integer> copiedSet = strategy.deepCopy(originalSet);

        assertNotSame(originalSet, copiedSet);
        assertEquals(originalSet, copiedSet);
    }

    private void fillCollectionWithIntegers(Collection<Integer> collection) {
        collection.add(INT_1);
        collection.add(INT_2);
        collection.add(INT_3);
    }
}
