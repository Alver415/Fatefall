package com.alver.fatefall.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.beans.property.SimpleListProperty;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class SimpleListPropertySerializer extends StdSerializer<SimpleListProperty> {

    protected SimpleListPropertySerializer() {
        this(SimpleListProperty.class);
    }
    protected SimpleListPropertySerializer(Class<SimpleListProperty> type) {
        super(type);
    }

    @Override
    public void serialize(SimpleListProperty value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Object element : value) {
            gen.writeObject(element);
        }
        gen.writeEndArray();
    }

}
