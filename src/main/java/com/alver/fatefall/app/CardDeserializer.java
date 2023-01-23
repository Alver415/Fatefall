package com.alver.fatefall.app;

import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.attributes.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

@Component
public class CardDeserializer extends StdDeserializer<Card> {

    @Autowired
    protected ObjectWriter writer;
    @Autowired
    protected AttributeFactory attributeFactory;

    public CardDeserializer() {
        this(Card.class);
    }

    public CardDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Card deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        return buildCard(parser.getCodec().readTree(parser));
    }

    public Card buildCard(JsonNode json) {
        Card card = new Card();
        for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            JsonNode value = json.get(field);
            card.addAttribute(buildAttribute(field, value));
        }
        return card;
    }

    protected <T> Attribute<T> buildAttribute(String field, JsonNode json) {
        Class<Attribute<T>> type = convertToAttributeType(json.getNodeType());
        T value = extractValue(type, json);
        Attribute<T> attribute = attributeFactory.createAttribute(field, type, value);
        attribute.setName(field);

        if (json.getNodeType().equals(JsonNodeType.OBJECT)) {
            for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
                String childField = it.next();
                JsonNode childJson = json.get(childField);
                attribute.addAttribute(buildAttribute(childField, childJson));
            }
        }
        if (json.getNodeType().equals(JsonNodeType.ARRAY)) {
            int i = 0;
            for (Iterator<JsonNode> it = json.elements(); it.hasNext(); i++) {
                JsonNode childJson = it.next();
                attribute.addAttribute(buildAttribute("[%d]".formatted(i), childJson));
            }
        }
        return attribute;
    }

    private <T extends Attribute<V>, V> V extractValue(Class<T> clazz, JsonNode json) {
        if (StringAttribute.class.equals(clazz)) {
            return (V) json.asText();
        } else if (DoubleAttribute.class.equals(clazz)) {
            return (V) (Double) json.asDouble();
        } else if (IntegerAttribute.class.equals(clazz)) {
            return (V) (Integer) json.asInt();
        } else if (BooleanAttribute.class.equals(clazz)) {
            return (V) (Boolean) json.asBoolean();
        }
        System.out.printf("WARNING - Failed to extract json value to class '%s': %s%n", clazz, json);
        return null;
    }

    @SuppressWarnings("unchecked")
    protected <T> Class<T> convertToAttributeType(JsonNodeType nodeType) {
        return switch (nodeType) {
            case NUMBER -> (Class<T>) DoubleAttribute.class;
            case BOOLEAN -> (Class<T>) BooleanAttribute.class;
            default -> (Class<T>) StringAttribute.class;
        };
    }
}