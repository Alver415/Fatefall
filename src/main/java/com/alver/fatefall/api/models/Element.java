package com.alver.fatefall.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "element")
public class Element extends BaseEntity {

    protected StringProperty value = new SimpleStringProperty();

    protected DoubleProperty top = new SimpleDoubleProperty(this, "TOP", 0);
    protected DoubleProperty right = new SimpleDoubleProperty(this, "RIGHT", 0);
    protected DoubleProperty bottom = new SimpleDoubleProperty(this, "BOTTOM", 0);
    protected DoubleProperty left = new SimpleDoubleProperty(this, "LEFT", 0);

    public Element(){
    }
    public Element(String name){
        setName(name);
    }
    public Element(String name, String value){
        setName(name);
        setValue(value);
    }

    @Lob
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

    @Column(name = "anchor_top")
    public Double getTop() {
        return topProperty().get();
    }

    public void setTop(Double top) {
        topProperty().set(top);
    }

    @Column(name = "anchor_right")
    public Double getRight() {
        return rightProperty().get();
    }

    public void setRight(Double right) {
        rightProperty().set(right);
    }

    @Column(name = "anchor_bottom")
    public Double getBottom() {
        return bottomProperty().get();
    }

    public void setBottom(Double bottom) {
        bottomProperty().set(bottom);
    }

    @Column(name = "anchor_left")
    public Double getLeft() {
        return leftProperty().get();
    }

    public void setLeft(Double left) {
        leftProperty().set(left);
    }

}
