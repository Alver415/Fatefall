package com.alver.fatefall.json;

import com.alver.fatefall.app.fx.model.SimpleTreeProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class SimpleTreePropertyDeserializer extends JsonDeserializer<SimpleTreeProperty<?>> implements ContextualDeserializer {

    private JavaType elementType;

    @Override
    public JsonDeserializer<?> createContextual(
            DeserializationContext ctxt,
            BeanProperty property) {
        this.elementType = property.getType().containedType(0);
        return this;
    }

    @Override
    public SimpleTreeProperty<?> deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        SimpleTreeProperty<Object> tree = new SimpleTreeProperty<>();
        for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            String key = entry.getKey();
            SimpleTreeProperty<Object> value = deserializationContext.readTreeAsValue(entry.getValue(), elementType);
            if (!Objects.equals(value.getId(), key)){
                throw new IllegalStateException("key=%s, id=%s".formatted(key, value.getId()));
            }
            tree.mergeChild(value);
        }
        return tree;
    }
}
