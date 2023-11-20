package com.alver.fatefall.fx.app.view.console;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DefaultValueFactory<T> implements Callback<TableColumn.CellDataFeatures<T, T>, ObservableValue<T>> {
    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<T, T> param) {
        return new SimpleObjectProperty<>(param.getValue());
    }
}
