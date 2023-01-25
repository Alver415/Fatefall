package com.alver.fatefall.api.models;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;

public abstract class AbstractEntity {

    protected String id;
    protected String name;
    protected MapProperty<String, Attribute> children = new SimpleMapProperty<>(FXCollections.observableHashMap());

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(Attribute child) {
        children.put(child.getName(), child);
    }

    public Attribute getChild(String name) {
        return children.get(name);
    }

    public MapProperty<String, Attribute> getChildren() {
        return children;
    }

}
