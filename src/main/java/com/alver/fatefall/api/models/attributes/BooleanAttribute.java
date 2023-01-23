package com.alver.fatefall.api.models.attributes;

import com.alver.fatefall.api.models.Attribute;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;

public class BooleanAttribute extends Attribute<Boolean> {

    protected BooleanProperty wrappedProperty = new SimpleBooleanProperty();

    @Override
    protected Property<Boolean> wrappedProperty() {
        return wrappedProperty;
    }
}
