package com.alver.fatefall.utils;

import javafx.collections.ObservableList;

import java.util.function.Function;

public class CollectionBindings {

    public static <T> ListBinding<T, T> bind(
            ObservableList<? extends T> source,
            ObservableList<T> target) {
        return bind(source, target, Function.identity());
    }

    public static <S, T> ListBinding<S, T> bind(
            ObservableList<? extends S> source,
            ObservableList<T> target,
            Function<? super S, ? extends T> mappingFunction) {
        ListBinding<S, T> binding = new ListBinding<>(target, mappingFunction);
        target.setAll(source.stream().map(mappingFunction).toList());
        source.removeListener(binding);
        source.addListener(binding);
        return binding;
    }

}

