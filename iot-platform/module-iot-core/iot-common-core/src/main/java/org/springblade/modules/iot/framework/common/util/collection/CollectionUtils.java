package org.springblade.modules.iot.framework.common.util.collection;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * CollectionUtils adapter.
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static <T, K> Map<K, T> convertMap(Collection<T> collection, Function<T, K> keyMapper) {
        return collection.stream().collect(Collectors.toMap(keyMapper, Function.identity(), (a, b) -> a));
    }

    public static <T, K> Map<K, List<T>> convertMultiMap(Collection<T> collection, Function<T, K> keyMapper) {
        return collection.stream().collect(Collectors.groupingBy(keyMapper));
    }

    public static <T, R> List<R> convertList(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T> List<T> filterList(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> Set<T> convertSet(Collection<T> collection, Function<T, T> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }
}
