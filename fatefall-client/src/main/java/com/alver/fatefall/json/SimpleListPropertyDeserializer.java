package com.alver.fatefall.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class SimpleListPropertyDeserializer extends JsonDeserializer<SimpleListProperty<?>> implements ContextualDeserializer {

    private JavaType elementType;

    @Override
    public JsonDeserializer<?> createContextual(
            DeserializationContext ctxt,
            BeanProperty property) {
        this.elementType = property.getType().containedType(0);
        return this;
    }

    @Override
    public SimpleListProperty<?> deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        ObservableList<?> list = FXCollections.observableArrayList();
        for (JsonNode itemNode : node) {
            list.add(deserializationContext.readTreeAsValue(itemNode, elementType));
        }
        return new SimpleListProperty<>(list);
    }
}
