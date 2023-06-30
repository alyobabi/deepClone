package org.example.creator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.example.CopyUtils;

public class ParamsObjectCreator<T> implements ObjectCreator<T> {
    @Override
    public Optional<T> createCopiedObject(T obj) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<T> clazz = (Class<T>) obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i = 1; i <= declaredFields.length; i++) {
            List<Field[]> combinations = generateFieldCombinations(declaredFields, i);

            for (Field[] fields : combinations) {
                List<Field[]> permutations = generateFieldPermutations(fields);

                for (Field[] permutation : permutations) {
                    Optional<Constructor<T>> constructor = getConstructor(clazz, permutation);

                    if (constructor.isPresent()) {
                        return Optional.of(constructor.get().newInstance(getParams(obj, permutation)));
                    }
                }
            }
        }
        return Optional.empty();
    }

    private List<Field[]> generateFieldCombinations(Field[] fields, int length) {
        List<Field[]> combinations = new ArrayList<>();
        generateFieldCombinations(fields, length, 0, new Field[length], combinations);
        return combinations;
    }

    private void generateFieldCombinations(Field[] fields, int length, int start,
                                           Field[] combination, List<Field[]> combinations) {
        if (length == 0) {
            combinations.add(combination.clone());
            return;
        }

        for (int i = start; i <= fields.length - length; i++) {
            combination[combination.length - length] = fields[i];
            generateFieldCombinations(fields, length - 1, i + 1, combination, combinations);
        }
    }

    private List<Field[]> generateFieldPermutations(Field[] fields) {
        List<Field[]> permutations = new ArrayList<>();
        generateFieldPermutations(fields, 0, permutations);
        return permutations;
    }

    private void generateFieldPermutations(Field[] fields, int index, List<Field[]> permutations) {
        if (index == fields.length - 1) {
            permutations.add(fields.clone());
            return;
        }

        for (int i = index; i < fields.length; i++) {
            swap(fields, index, i);
            generateFieldPermutations(fields, index + 1, permutations);
            swap(fields, index, i);
        }
    }

    private void swap(Field[] fields, int i, int j) {
        Field temp = fields[i];
        fields[i] = fields[j];
        fields[j] = temp;
    }


    private Object[] getParams(T obj, Field[] fields) throws IllegalAccessException {
        Object[] params = new Object[fields.length];
        for (int i = 0; i < params.length; i++) {
            fields[i].setAccessible(true);
            Object fieldValue = fields[i].get(obj);
            if (Objects.nonNull(fieldValue)) {
                params[i] = CopyUtils.deepCopy(fieldValue);
            }
        }
        return params;
    }

    private Optional<Constructor<T>> getConstructor(Class<T> clazz, Field[] declaredFields) {
        List<? extends Class<?>> parameterTypes = Arrays.stream(declaredFields).map(Field::getType).toList();
        Class[] parameterTypesArray = parameterTypes.toArray(new Class[0]);
        try {
            return Optional.of(clazz.getDeclaredConstructor(parameterTypesArray));
        } catch (NoSuchMethodException exception) {
            return Optional.empty();
        }
    }
}

