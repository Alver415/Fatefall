package com.alver.fatefall.app.fx.components.settings;

import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;

public interface Setting<T> extends ObservableValue<T>, PropertySheet.Item{

    T get();
    void set(T value);
    void set(String value);

    Class<T> getType();
    String getName();
    String getDescription();
    String getCategory();
}
