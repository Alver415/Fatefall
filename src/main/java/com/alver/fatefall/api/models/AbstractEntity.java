package com.alver.fatefall.api.models;

import com.alver.fatefall.api.models.attributes.BooleanAttribute;
import com.alver.fatefall.api.models.attributes.DoubleAttribute;
import com.alver.fatefall.api.models.attributes.IntegerAttribute;
import com.alver.fatefall.api.models.attributes.StringAttribute;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public abstract class AbstractEntity {

    protected String id;
    protected String name;
    protected MapProperty<String, StringAttribute> stringMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
    protected MapProperty<String, IntegerAttribute> integerMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
    protected MapProperty<String, DoubleAttribute> doubleMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
    protected MapProperty<String, BooleanAttribute> booleanMap = new SimpleMapProperty<>(FXCollections.observableHashMap());

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

    public <T> void addAttribute(Attribute<T> child) {
        if (child instanceof StringAttribute attr) stringMap.put(attr.getName(), attr);
        else if (child instanceof IntegerAttribute attr) integerMap.put(attr.getName(), attr);
        else if (child instanceof DoubleAttribute attr) doubleMap.put(attr.getName(), attr);
        else if (child instanceof BooleanAttribute attr) booleanMap.put(attr.getName(), attr);
        else throw new IllegalArgumentException("Unrecognized type: " + child.getClass());
    }

    public Attribute<?> getAttribute(String name) {
        if (stringMap.containsKey(name)) return stringMap.get(name);
        if (integerMap.containsKey(name)) return integerMap.get(name);
        if (doubleMap.containsKey(name)) return doubleMap.get(name);
        if (booleanMap.containsKey(name)) return booleanMap.get(name);
        return null;
    }

    public <T extends Attribute<S>, S> T getAttribute(String name, Class<T> type) {
        if (type.equals(StringAttribute.class)) return (T) stringMap.get(name);
        else if (type.equals(IntegerAttribute.class)) return (T) integerMap.get(name);
        else if (type.equals(DoubleAttribute.class)) return (T) doubleMap.get(name);
        else if (type.equals(BooleanAttribute.class)) return (T) booleanMap.get(name);
        else throw new IllegalArgumentException("Unrecognized type: " + type);
    }

    public ObservableList<Attribute> getAttributes() {
        ObservableList<Attribute> children = FXCollections.observableArrayList();
        children.addAll(stringMap.values());
        children.addAll(integerMap.values());
        children.addAll(doubleMap.values());
        children.addAll(booleanMap.values());
        return children;
    }

    public <S extends Attribute<N>, N> Collection<S> getAttributes(Class<S> type) {
        if (type.equals(StringAttribute.class)) return (Collection<S>) stringMap.values();
        if (type.equals(IntegerAttribute.class)) return (Collection<S>) integerMap.values();
        if (type.equals(DoubleAttribute.class)) return (Collection<S>) doubleMap.values();
        if (type.equals(BooleanAttribute.class)) return (Collection<S>) booleanMap.values();
        throw new IllegalArgumentException("Unrecognized type: " + type);
    }

}
