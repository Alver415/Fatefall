package com.alver.fatefall.fx.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.beans.property.SimpleMapProperty;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class SimpleMapPropertySerializer extends StdSerializer<SimpleMapProperty> {
    protected SimpleMapPropertySerializer() {
        this(SimpleMapProperty.class);
    }
    protected SimpleMapPropertySerializer(Class<SimpleMapProperty> type) {
        super(type);
    }

    @Override
    public void serialize(SimpleMapProperty value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        for (Object key : value.keySet()) {
            gen.writeObjectField(key.toString(), value.get(key));
        }
        gen.writeEndObject();
    }

}
