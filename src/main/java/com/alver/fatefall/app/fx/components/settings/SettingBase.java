package com.alver.fatefall.app.fx.components.settings;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import java.util.Optional;

public abstract class SettingBase<T> extends SimpleObjectProperty<T> implements Setting<T> {

    protected String description;
    protected String category;

    public SettingBase(Object bean, String name, String description, T initialValue) {
        super(bean, name, initialValue);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setValue(Object value) {
        super.set((T)value);
    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.of(this);
    }
}
