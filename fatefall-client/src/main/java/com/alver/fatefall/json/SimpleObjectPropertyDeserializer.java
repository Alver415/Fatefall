package com.alver.fatefall.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class SimpleObjectPropertyDeserializer extends JsonDeserializer<SimpleObjectProperty> implements ContextualDeserializer {

    private JavaType elementType;

    @Override
    public JsonDeserializer createContextual(
            DeserializationContext ctxt,
            BeanProperty property) {
        this.elementType = property.getType().containedType(0);
        return this;
    }

    @Override
    public SimpleObjectProperty deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        return new SimpleObjectProperty(deserializationContext.readTreeAsValue(node, elementType));
    }
}
