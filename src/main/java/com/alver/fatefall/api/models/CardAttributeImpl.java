package com.alver.fatefall.api.models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

public class CardAttributeImpl<T> implements CardAttribute<T> {

    protected StringProperty id = new SimpleStringProperty();
    protected StringProperty name = new SimpleStringProperty();
    protected ObjectProperty<Class<T>> type = new SimpleObjectProperty<>();
    protected StringProperty data = new SimpleStringProperty();
    protected ObjectProperty<T> property = new SimpleObjectProperty<>();
    protected ListProperty<CardAttribute<?>> children = new SimpleListProperty<>(FXCollections.observableArrayList());

    public StringProperty idProperty() {
        return id;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public ObjectProperty<Class<T>> typeProperty() {
        return type;
    }
    public StringProperty dataProperty() {
        return data;
    }
    public ListProperty<CardAttribute<?>> childrenProperty() {
        return children;
    }


    @Override
    public void bind(ObservableValue<? extends T> observable) {
        property.bind(observable);
    }

    @Override
    public void unbind() {
        property.unbind();
    }

    @Override
    public boolean isBound() {
        return property.isBound();
    }

    @Override
    public void bindBidirectional(Property<T> other) {
        property.bindBidirectional(other);
    }

    @Override
    public void unbindBidirectional(Property<T> other) {
        property.unbindBidirectional(other);
    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public void addListener(ChangeListener<? super T> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super T> listener) {
        property.removeListener(listener);
    }

    @Override
    public T getValue() {
        return property.getValue();
    }

    @Override
    public void setValue(T value) {
        property.setValue(value);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        property.removeListener(listener);
    }
}
