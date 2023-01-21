package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.models.CardAttribute;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class CardAttributeTreeTableView extends TreeTableView<CardAttribute<?>> {

    public CardAttributeTreeTableView() {
        setShowRoot(false);
        TreeTableColumn<CardAttribute<?>, String> fieldColumn = new TreeTableColumn<>("Field");
        fieldColumn.setCellValueFactory(cdf -> {
            try {
                return cdf.getValue().getValue().nameProperty();
            } catch (Exception e) {
                return null;
            }
        });
        TreeTableColumn<CardAttribute<?>, Object> valueColumn = new TreeTableColumn<>("Value");
        valueColumn.setCellValueFactory(cdf -> {
            try {
                return (ObservableValue<Object>) cdf.getValue().getValue().propertyProperty();
            } catch (Exception e) {
                return null;
            }
        });

        getColumns().setAll(fieldColumn, valueColumn);
    }
}
