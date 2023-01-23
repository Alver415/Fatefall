package com.alver.fatefall.api.models.attributes;

import com.alver.fatefall.api.models.Attribute;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;

public class DoubleAttribute extends Attribute<Double> {

    protected DoubleProperty wrappedProperty = new SimpleDoubleProperty();

    @Override
    protected Property<Double> wrappedProperty() {
        return wrappedProperty.asObject();
    }
}
