package com.alver.fatefall.api.models.attributes;

import com.alver.fatefall.api.models.Attribute;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AttributeFactory {

    <T extends Attribute<V>, V> T createAttribute(Class<T> type) {
        T attribute;
        if (Objects.equals(type, StringAttribute.class)) {
            attribute = (T) new StringAttribute();
        } else if (Objects.equals(type, DoubleAttribute.class)) {
            attribute = (T) new DoubleAttribute();
        } else if (Objects.equals(type, IntegerAttribute.class)) {
            attribute = (T) new IntegerAttribute();
        } else if (Objects.equals(type, BooleanAttribute.class)) {
            attribute = (T) new BooleanAttribute();
        } else {
            throw new RuntimeException("Unrecognized type: " + type);
        }
        return attribute;
    }

    <T extends Attribute<V>, V> T createAttribute(Class<T> type, V value) {
        T attribute = createAttribute(type);
        attribute.setValue(value);
        return attribute;
    }

    public <T extends Attribute<V>, V> T createAttribute(String name, Class<T> type, V value) {
        T attribute = createAttribute(type);
        attribute.setName(name);
        attribute.setValue(value);
        return attribute;
    }
}
