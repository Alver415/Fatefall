package com.alver.fatefall.api.models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class Attribute<T> extends AbstractEntity implements Property<T>{

    protected abstract Property<T> wrappedProperty();

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public T getValue() {
        return wrappedProperty().getValue();
    }

    @Override
    public void setValue(T value) {
        wrappedProperty().setValue(value);
    }

    @Override
    public void bind(ObservableValue<? extends T> observable) {
        wrappedProperty().bind(observable);
    }

    @Override
    public void unbind() {
        wrappedProperty().unbind();
    }

    @Override
    public boolean isBound() {
        return wrappedProperty().isBound();
    }

    @Override
    public void bindBidirectional(Property<T> other) {
        wrappedProperty().bindBidirectional(other);
    }

    @Override
    public void unbindBidirectional(Property<T> other) {
        wrappedProperty().unbindBidirectional(other);
    }

    @Override
    public void addListener(ChangeListener<? super T> listener) {
        wrappedProperty().addListener(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super T> listener) {
        wrappedProperty().removeListener(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        wrappedProperty().addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        wrappedProperty().removeListener(listener);
    }
}
