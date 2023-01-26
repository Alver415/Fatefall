package com.alver.fatefall.api.models;

import jakarta.persistence.*;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.hibernate.annotations.GenericGenerator;
import org.w3c.dom.Attr;

import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public abstract class BaseEntity {

    protected String id;
    protected StringProperty name = new SimpleStringProperty();
    protected Map<String, Attribute> attributes = new HashMap<>();

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty(){
        return name;
    }

    public void addAttribute(Attribute attribute) {
        attributes.put(attribute.getName(), attribute);
    }

    public Attribute getAttribute(String name) {
        return attributes.get(name);
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Map<String, Attribute> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, Attribute> attributes) {
        this.attributes = attributes;
    }

}
