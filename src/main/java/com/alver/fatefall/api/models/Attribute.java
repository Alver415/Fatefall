package com.alver.fatefall.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "attribute")
public class Attribute extends BaseEntity {

    protected StringProperty value = new SimpleStringProperty();

    public Attribute(){
    }
    public Attribute(String name){
        setName(name);
    }
    public Attribute(String name, String value){
        setName(name);
        setValue(value);
    }

    public StringProperty getValue() {
        return value;
    }
public void setValue(String value) {
        this.value.set(value);
    }
    public String valueProperty() {
        return value.get();
    }

}
