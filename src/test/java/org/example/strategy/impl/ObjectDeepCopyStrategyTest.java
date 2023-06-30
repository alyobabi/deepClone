package org.example.strategy.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.example.sample.Car;
import org.example.sample.Gender;
import org.example.sample.Man;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectDeepCopyStrategyTest {

    @Test
    public void shouldDeepCopyManObject() {
        List<String> originalBooks = Arrays.asList("Book1", "Book2", "Book3");
        Man originalMan = new Man("John Doe", 30, originalBooks);
        originalMan.setGender(Gender.OTHER);
        originalMan.setNice(true);

        Man copiedMan = new ObjectDeepCopyStrategy<Man>().deepCopy(originalMan);

        assertNotSame(originalMan, copiedMan);
        assertEquals(originalMan.getName(), copiedMan.getName());
        assertEquals(originalMan.getAge(), copiedMan.getAge());
        assertNotSame(originalMan.getFavoriteBooks(), copiedMan.getFavoriteBooks());
        assertEquals(originalMan.getFavoriteBooks(), copiedMan.getFavoriteBooks());
        assertEquals(originalMan.isNice(), copiedMan.isNice());
        assertEquals(originalMan.getGender(), copiedMan.getGender());
    }

    @Test
    public void shouldDeepCopyManObjectWithEmptyBooks() {
        List<String> originalBooks = new ArrayList<>();
        Man originalMan = new Man("Jane Doe", 25, originalBooks);

        Man copiedMan = new ObjectDeepCopyStrategy<Man>().deepCopy(originalMan);

        assertNotSame(originalMan, copiedMan);
        assertEquals(originalMan.getName(), copiedMan.getName());
        assertEquals(originalMan.getAge(), copiedMan.getAge());
        assertNotSame(originalMan.getFavoriteBooks(), copiedMan.getFavoriteBooks());
        assertEquals(originalMan.getFavoriteBooks(), copiedMan.getFavoriteBooks());
    }

    @Test
    public void shouldDeepCopyCarObjectWithReferenceOnItself() {
        Man originalMan = new Man("John Doe", 30, null);
        Car originalCar = new Car(originalMan);
        Set<Car> carSet = new HashSet<>();
        carSet.add(originalCar);
        originalCar.setCarSet(carSet);

        Car copiedCar = new ObjectDeepCopyStrategy<Car>().deepCopy(originalCar);

        assertNotSame(originalMan, copiedCar.getMan());
        assertEquals(originalMan.getName(), copiedCar.getMan().getName());
        assertEquals(originalMan.getAge(), copiedCar.getMan().getAge());
        assertNull(copiedCar.getMan().getFavoriteBooks());

        assertNotSame(originalCar, copiedCar);
        assertTrue(copiedCar.getCarSet().size() == 1 && copiedCar.getCarSet().contains(copiedCar));
    }
}

