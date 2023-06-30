package org.example.creator;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public interface ObjectCreator<T> {
    Optional<T> createCopiedObject(T obj) throws InvocationTargetException, InstantiationException, IllegalAccessException;
}
