package org.example.creator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class DefaultObjectCreator<T> implements ObjectCreator<T> {
    @Override
    public Optional<T> createCopiedObject(T obj) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Optional<Constructor<T>> constructorWithoutParam = getConstructorWithoutParam((Class<T>) obj.getClass());
        if (constructorWithoutParam.isPresent()) {
            Constructor<T> constructor = constructorWithoutParam.get();
            constructor.setAccessible(true);
            return Optional.of(constructor.newInstance());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Constructor<T>> getConstructorWithoutParam(Class<T> clazz) {
        try {
            return Optional.of(clazz.getDeclaredConstructor());
        } catch (NoSuchMethodException exception) {
            return Optional.empty();
        }
    }
}
