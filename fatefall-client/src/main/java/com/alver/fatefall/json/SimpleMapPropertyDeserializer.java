package com.alver.fatefall.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class SimpleMapPropertyDeserializer extends JsonDeserializer<SimpleMapProperty<String,?>> implements ContextualDeserializer {

    private JavaType elementType;

    @Override
    public JsonDeserializer<?> createContextual(
            DeserializationContext ctxt,
            BeanProperty property) {
        this.elementType = property.getType().containedType(0);
        return this;
    }

    @Override
    public SimpleMapProperty<String,?> deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        ObservableMap<String, Object> map = FXCollections.observableHashMap();
        for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            String key = entry.getKey();
            Object value = deserializationContext.readTreeAsValue(entry.getValue(), elementType);
            map.put(key, value);
        }
        return new SimpleMapProperty<>(map);
    }
}
