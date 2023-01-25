package com.alver.fatefall.api.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attribute extends AbstractEntity {

    protected StringProperty value = new SimpleStringProperty();

    protected DoubleProperty top = new SimpleDoubleProperty(this, "TOP", 0);
    protected DoubleProperty right = new SimpleDoubleProperty(this, "RIGHT", 0);
    protected DoubleProperty bottom = new SimpleDoubleProperty(this, "BOTTOM", 0);
    protected DoubleProperty left = new SimpleDoubleProperty(this, "LEFT", 0);

    public Attribute(){
    }
    public Attribute(String name){
        this.name = name;
    }
    public Attribute(String name, String value){
        this.name = name;
        this.value.set(value);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public DoubleProperty topProperty() {
        return top;
    }

    public DoubleProperty rightProperty() {
        return right;
    }

    public DoubleProperty bottomProperty() {
        return bottom;
    }

    public DoubleProperty leftProperty() {
        return left;
    }

    public Double getTop() {
        return topProperty().get();
    }

    public void setTop(Double top) {
        topProperty().set(top);
    }

    public Double getRight() {
        return rightProperty().get();
    }

    public void setRight(Double right) {
        rightProperty().set(right);
    }

    public Double getBottom() {
        return bottomProperty().get();
    }

    public void setBottom(Double bottom) {
        bottomProperty().set(bottom);
    }

    public Double getLeft() {
        return leftProperty().get();
    }

    public void setLeft(Double left) {
        leftProperty().set(left);
    }

}
