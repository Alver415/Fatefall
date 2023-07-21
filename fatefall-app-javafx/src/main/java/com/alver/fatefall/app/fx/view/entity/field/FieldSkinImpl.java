package com.alver.fatefall.app.fx.view.entity.field;

import com.sun.javafx.scene.control.DoubleField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class FieldSkinImpl extends SkinBase<FieldControl> {

    protected final BooleanProperty expandedProperty = new SimpleBooleanProperty(false);

    protected final VBox root;
    protected final HBox standardFields;
    protected final VBox layoutFields;
    protected final Button expandButton;
    protected final TextField valueField;
    protected final DoubleField topField;
    protected final DoubleField bottomField;
    protected final DoubleField rightField;
    protected final DoubleField leftField;

    protected FieldSkinImpl(FieldControl control) {
        super(control);

        this.valueField = new TextField();
        this.topField = new DoubleField();
        this.bottomField = new DoubleField();
        this.rightField = new DoubleField();
        this.leftField = new DoubleField();

        this.valueField.setPromptText("value");
        this.topField.setPromptText("top");
        this.bottomField.setPromptText("bottom");
        this.rightField.setPromptText("right");
        this.leftField.setPromptText("left");

        control.attributeProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
//                this.valueField.textProperty().unbindBidirectional(oldValue.valueProperty());
//                this.topField.valueProperty().unbindBidirectional(oldValue.topProperty());
//                this.rightField.valueProperty().unbindBidirectional(oldValue.rightProperty());
//                this.bottomField.valueProperty().unbindBidirectional(oldValue.bottomProperty());
//                this.leftField.valueProperty().unbindBidirectional(oldValue.leftProperty());
            }
            if (newValue != null) {
//                this.valueField.textProperty().bindBidirectional(newValue.valueProperty());
//                this.topField.valueProperty().bindBidirectional(newValue.topProperty());
//                this.rightField.valueProperty().bindBidirectional(newValue.rightProperty());
//                this.bottomField.valueProperty().bindBidirectional(newValue.bottomProperty());
//                this.leftField.valueProperty().bindBidirectional(newValue.leftProperty());
            }
        });

        this.expandButton = new Button();
        this.expandButton.setOnAction(e -> toggle());
        this.standardFields = new HBox(valueField, expandButton);
        this.layoutFields = new VBox(topField, rightField, bottomField, leftField);

        this.root = new VBox(standardFields, layoutFields);

        this.expandedProperty.addListener((observable, oldValue, newValue) -> {
            double height = newValue ? 0 : Region.USE_COMPUTED_SIZE;
            layoutFields.setMaxHeight(height);
            layoutFields.setPrefHeight(height);
            layoutFields.setMinHeight(height);

            expandButton.setText(newValue ? "Expand" : "Collapse");
        });
        this.expandedProperty.set(true);

        getChildren().add(root);

    }

    public void toggle() {
        expandedProperty.set(!expandedProperty.get());
    }
}
