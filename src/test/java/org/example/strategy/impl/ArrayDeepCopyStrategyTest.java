package org.example.strategy.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class ArrayDeepCopyStrategyTest {

    private ArrayDeepCopyStrategy arrayDeepCopyStrategy;

    @BeforeEach
    public void setUp() {
        arrayDeepCopyStrategy = new ArrayDeepCopyStrategy<>();
    }
    @Test
    public void shouldDeepCopyStringArray() {
        String[] array = new String[]{"a", "b", "c"};

        String[] copiedArray = (String[]) arrayDeepCopyStrategy.deepCopy(array);

        assertNotSame(array, copiedArray);
        assertArrayEquals(array, copiedArray);
    }

    @Test
    public void shouldDeepCopyEmptyArray() {
        Object[] array = new Object[0];

        Object[] copiedArray = arrayDeepCopyStrategy.deepCopy(array);

        assertNotSame(array, copiedArray);
        assertArrayEquals(array, copiedArray);
    }
}