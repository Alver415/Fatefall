package com.alver.fatefall.app.fx.model.property;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface DelegatingMap<K, V> extends Map<K, V> {

    Map<K, V> getDelegateMap();

    @Override
    default int size() {
        return getDelegateMap().size();
    }

    @Override
    default boolean isEmpty() {
        return getDelegateMap().isEmpty();
    }

    @Override
    default boolean containsKey(Object key) {
        return getDelegateMap().containsKey(key);
    }

    @Override
    default boolean containsValue(Object value) {
        return getDelegateMap().containsValue(value);
    }

    @Override
    default V get(Object key) {
        return getDelegateMap().get(key);
    }

    @Override
    default V put(K key, V value) {
        return getDelegateMap().put(key, value);
    }

    @Override
    default V remove(Object key) {
        return getDelegateMap().remove(key);
    }

    @Override
    default void putAll(Map<? extends K, ? extends V> other) {
        getDelegateMap().putAll(other);
    }

    @Override
    default void clear() {
        getDelegateMap().clear();
    }

    @Override
    default Set<K> keySet() {
        return getDelegateMap().keySet();
    }

    @Override
    default Collection<V> values() {
        return getDelegateMap().values();
    }

    @Override
    default Set<Entry<K, V>> entrySet() {
        return getDelegateMap().entrySet();
    }
}
