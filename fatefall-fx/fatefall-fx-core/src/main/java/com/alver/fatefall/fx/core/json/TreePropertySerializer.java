package com.alver.fatefall.fx.core.json;

import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.collections.ObservableMap;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class TreePropertySerializer extends StdSerializer<TreeProperty> {

    public TreePropertySerializer() {
        this(TreeProperty.class);
    }

    protected TreePropertySerializer(Class<TreeProperty> type) {
        super(type);
    }

    @Override
    public void serialize(TreeProperty property, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        serialize(property, gen);
    }

    private static void serialize(TreeProperty property, JsonGenerator gen) throws IOException {
        Object value = property.getValue();
        ObservableMap children = property.getChildrenMap();
        if (!children.isEmpty()) {
            gen.writeStartObject();
            for (Object key : children.keySet()) {
                Object child = children.get(key);
                gen.writeFieldName(key.toString());
                gen.writeObject(children.get(key));
            }
            gen.writeEndObject();
        } else if (value == null) {
            gen.writeNull();
        } else if (value instanceof String stringValue) {
            gen.writeString(stringValue);
        } else if (value instanceof Boolean booleanValue) {
            gen.writeBoolean(booleanValue);
        } else if (value instanceof Integer intValue) {
            gen.writeNumber(intValue);
        } else if (value instanceof Long longValue) {
            gen.writeNumber(longValue);
        } else if (value instanceof Double doubleValue) {
            gen.writeNumber(doubleValue);
        } else if (value instanceof Float floatValue) {
            gen.writeNumber(floatValue);
        }
    }

}
