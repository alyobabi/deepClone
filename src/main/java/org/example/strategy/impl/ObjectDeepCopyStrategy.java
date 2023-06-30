package org.example.strategy.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import org.example.CopyUtils;
import org.example.creator.DefaultObjectCreator;
import org.example.creator.ObjectCreator;
import org.example.creator.ParamsObjectCreator;
import org.example.exception.DeepCopyException;
import org.example.strategy.DeepCopyStrategy;

public class ObjectDeepCopyStrategy<T> implements DeepCopyStrategy<T> {
    @Override
    public T deepCopy(T originalObject) throws DeepCopyException {
        try {
            T copiedObject = createObject(originalObject);
            fillField(originalObject, copiedObject);
            return copiedObject;
        } catch (Exception e) {
            throw new DeepCopyException("Error occurred during deep copy", e);
        }
    }

    private void fillField(T obj, T copiedObject) throws Exception {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            if (Objects.nonNull(fieldValue)) {
                field.set(copiedObject, CopyUtils.deepCopy(fieldValue));
            }
        }
    }

    public T createObject(T obj) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (ObjectCreator<T> creator :
                Arrays.asList(new DefaultObjectCreator<T>(), new ParamsObjectCreator<T>())) {
            Optional<T> copiedObject = creator.createCopiedObject(obj);
            if (copiedObject.isPresent()) {
                CopyUtils.addCopiedObject(obj, copiedObject.get());
                return copiedObject.get();
            }
        }
        throw new DeepCopyException("Object isn't created due no constructor is found");
    }
}
