package com.alver.fatefall.api.models;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public abstract class BaseEntity {

    protected String id;
    protected StringProperty name = new SimpleStringProperty();
    protected Map<String, Element> attributes = new HashMap<>();

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

    public void addAttribute(Element attribute) {
        attributes.put(attribute.getName(), attribute);
    }

    public Element getAttribute(String name) {
        return attributes.get(name);
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Map<String, Element> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, Element> attributes) {
        this.attributes = attributes;
    }

}
