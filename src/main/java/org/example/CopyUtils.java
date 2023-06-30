package org.example;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import org.example.exception.DeepCopyException;
import org.example.strategy.impl.ArrayDeepCopyStrategy;
import org.example.strategy.impl.CollectionDeepCopyStrategy;
import org.example.strategy.impl.ImmutableValueDeepCopyStrategy;
import org.example.strategy.impl.MapDeepCopyStrategy;
import org.example.strategy.impl.ObjectDeepCopyStrategy;

/**
 * Utility class for deep copying objects
 */
public class CopyUtils {

    private static final Map<Object, Object> copiedObjects = new IdentityHashMap<>();

    public static void addCopiedObject(Object originalObject, Object copiedObject) {
        copiedObjects.put(originalObject, copiedObject);
    }

    /**
     * Deep copies the given object
     *
     * @param originalObject the object to be copied
     * @param <T>            the type of the object
     * @return the copied object
     * @throws DeepCopyException if an error occurs during deep copy
     */
    public static <T> T deepCopy(T originalObject) throws DeepCopyException {
        if (originalObject == null) {
            return null;
        }
        if (copiedObjects.containsKey(originalObject)) {
            return (T) copiedObjects.get(originalObject);
        }

        T copiedObject;

        if (originalObject.getClass().isArray()) {
            copiedObject = (T) new ArrayDeepCopyStrategy<>().deepCopy((Object[]) originalObject);
        } else if (originalObject instanceof Collection<?>) {
            copiedObject = (T) new CollectionDeepCopyStrategy<>().deepCopy((Collection<Object>) originalObject);
        } else if (originalObject instanceof Map<?, ?>) {
            copiedObject = (T) new MapDeepCopyStrategy<>().deepCopy((Map<Object, Object>) originalObject);
        } else if (isImmutableValueType(originalObject)) {
            copiedObject = new ImmutableValueDeepCopyStrategy<T>().deepCopy(originalObject);
        } else {
            copiedObject = new ObjectDeepCopyStrategy<T>().deepCopy(originalObject);
        }
        addCopiedObject(originalObject, copiedObject);
        return copiedObject;
    }

    private static boolean isImmutableValueType(Object obj) {
        return obj instanceof Number ||
                obj instanceof Character ||
                obj instanceof Boolean ||
                obj instanceof Enum ||
                obj instanceof String;
    }
}
