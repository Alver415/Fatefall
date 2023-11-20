package com.alver.fatefall.fx.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class SimpleObjectPropertySerializer extends StdSerializer<SimpleObjectProperty> {

    protected SimpleObjectPropertySerializer() {
        this(SimpleObjectProperty.class);
    }
    protected SimpleObjectPropertySerializer(Class<SimpleObjectProperty> type) {
        super(type);
    }

    @Override
    public void serialize(SimpleObjectProperty value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.get());
    }
}
