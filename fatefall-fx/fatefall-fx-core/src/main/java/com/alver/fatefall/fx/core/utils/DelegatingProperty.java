package com.alver.fatefall.fx.core.utils;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public interface DelegatingProperty<T> extends Property<T> {

    Property<T> getDelegateProperty();
    
    @Override
    default void bind(ObservableValue<? extends T> observable) {
        getDelegateProperty().bind(observable);
    }

    @Override
    default void unbind() {
        getDelegateProperty().unbind();
    }

    @Override
    default boolean isBound() {
        return getDelegateProperty().isBound();
    }

    @Override
    default void bindBidirectional(Property<T> other) {
        getDelegateProperty().bindBidirectional(other);
    }

    @Override
    default void unbindBidirectional(Property<T> other) {
        getDelegateProperty().unbindBidirectional(other);
    }

    @Override
    default Object getBean() {
        return getDelegateProperty().getBean();
    }

    @Override
    default String getName() {
        return getDelegateProperty().getName();
    }

    @Override
    default void addListener(ChangeListener<? super T> listener) {
        getDelegateProperty().addListener(listener);
    }

    @Override
    default void removeListener(ChangeListener<? super T> listener) {
        getDelegateProperty().removeListener(listener);
    }

    @Override
    default T getValue() {
        return getDelegateProperty().getValue();
    }

    @Override
    default void setValue(T value) {
        getDelegateProperty().setValue(value);
    }

    @Override
    default void addListener(InvalidationListener listener) {
        getDelegateProperty().addListener(listener);
    }

    @Override
    default void removeListener(InvalidationListener listener) {
        getDelegateProperty().removeListener(listener);
    }
}
