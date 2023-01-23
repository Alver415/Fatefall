package com.alver.fatefall.api.models.attributes;

import com.alver.fatefall.api.models.Attribute;
import javafx.beans.property.*;

public class StringAttribute extends Attribute<String> {

    protected StringProperty wrappedProperty = new SimpleStringProperty();

    @Override
    protected Property<String> wrappedProperty() {
        return wrappedProperty;
    }
}
