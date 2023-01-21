package com.alver.fatefall.api.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public interface CardAttribute<T> extends Property<T>{

    StringProperty idProperty();
    default String getId() {
        return idProperty().get();
    }
    default void setId(String id) {
        idProperty().set(id);
    }

    StringProperty nameProperty();
    default String getName() {
        return nameProperty().get();
    }
    default void setName(String name) {
        nameProperty().set(name);
    }

    ObjectProperty<Class<T>> typeProperty();
    default Class<T> getType() {
        return typeProperty().get();
    }
    default void setType(Class<T> type) {
        typeProperty().set(type);
    }

    StringProperty dataProperty();
    default String getData() {
        return dataProperty().get();
    }
    default void setData(String data) {
        this.dataProperty().set(data);
    }

    ListProperty<CardAttribute<?>> childrenProperty();
    default ObservableList<CardAttribute<?>> getChildren() {
        return childrenProperty().get();
    }
    default void setChildren(ObservableList<CardAttribute<?>> children) {
        childrenProperty().set(children);
    }

    default T get() {
        return getValue();
    }

    default void set(T value) {
        setValue(value);
    }
}
