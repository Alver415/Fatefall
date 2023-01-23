package com.alver.fatefall.api.models.attributes;

import com.alver.fatefall.api.models.Attribute;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerAttribute extends Attribute<Integer> {

    protected IntegerProperty wrappedProperty = new SimpleIntegerProperty();

    @Override
    protected Property<Integer> wrappedProperty() {
        return wrappedProperty.asObject();
    }
}
