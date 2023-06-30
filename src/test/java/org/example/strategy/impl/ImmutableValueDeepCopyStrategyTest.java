package org.example.strategy.impl;

import org.example.sample.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ImmutableValueDeepCopyStrategyTest {

    @Test
    public void shouldDeepCopyInteger() {
        Integer originalInteger = 42;

        Integer copiedInteger = new ImmutableValueDeepCopyStrategy<Integer>().deepCopy(originalInteger);

        assertSame(originalInteger, copiedInteger);
    }

    @Test
    public void shouldDeepCopyByte() {
        Byte originalByte = 10;

        Byte copiedByte = new ImmutableValueDeepCopyStrategy<Byte>().deepCopy(originalByte);

        assertSame(originalByte, copiedByte);
    }

    @Test
    public void shouldDeepCopyDouble() {
        Double originalDouble = 3.14;

        Double copiedDouble = new ImmutableValueDeepCopyStrategy<Double>().deepCopy(originalDouble);

        assertSame(originalDouble, copiedDouble);
    }

    @Test
    public void shouldDeepCopyCharacter() {
        Character originalCharacter = 'A';

        Character copiedCharacter = new ImmutableValueDeepCopyStrategy<Character>().deepCopy(originalCharacter);

        assertSame(originalCharacter, copiedCharacter);
    }

    @Test
    public void shouldDeepCopyBoolean() {
        Boolean originalBoolean = true;

        Boolean copiedBoolean = new ImmutableValueDeepCopyStrategy<Boolean>().deepCopy(originalBoolean);

        assertSame(originalBoolean, copiedBoolean);
    }

    @Test
    public void shouldDeepCopyEnum() {
        Gender originalEnum = Gender.FEMALE;

        Gender copiedEnum = new ImmutableValueDeepCopyStrategy<Gender>().deepCopy(originalEnum);

        assertSame(originalEnum, copiedEnum);
    }

    @Test
    public void shouldDeepCopyString() {
        String originalString = "Hello, world!";

        String copiedString = new ImmutableValueDeepCopyStrategy<String>().deepCopy(originalString);

        assertSame(originalString, copiedString);
    }
}
