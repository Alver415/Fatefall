package com.alver.fatefall.app;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardAttribute;
import com.alver.fatefall.api.models.CardAttributeImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Component
public class CardDeserializer extends StdDeserializer<Card> {

    @Autowired
    protected ObjectWriter writer;

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

    protected Card buildCard(ObjectNode json) throws JsonProcessingException {
        Card card = new Card();
        for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            JsonNode value = json.get(field);
            CardAttribute<?> attribute = buildAttribute(field, value);
            card.getAttributeList().add(attribute);
        }
        card.setData(writer.writeValueAsString(json));
        return card;
    }

    protected <T> CardAttribute<T> buildAttribute(String field, JsonNode json) {
        CardAttribute<T> attribute = new CardAttributeImpl<>();
        attribute.setType(convertType(json.getNodeType()));
        attribute.setName(field);
        attribute.setData(json.asText());

        if (json.getNodeType().equals(JsonNodeType.OBJECT)){
            for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
                String childField = it.next();
                JsonNode childJson = json.get(childField);
                CardAttribute<?> child = buildAttribute(childField, childJson);
                attribute.getChildren().add(child);
            }
        } else {
            attribute.set(extractValue(attribute.getType(), json));
        }
        return attribute;
    }

    private <T> T extractValue(Class<T> clazz, JsonNode json) {
        if (String.class.equals(clazz)){
            return (T) json.asText();
        } else if (Double.class.equals(clazz)){
            return (T) (Double) json.asDouble();
        } else if (Boolean.class.equals(clazz)){
            return (T) (Boolean) json.asBoolean();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    protected <T> Class<T> convertType(JsonNodeType nodeType) {
        return switch (nodeType) {
            case NUMBER -> (Class<T>) Double.class;
            case BOOLEAN -> (Class<T>) Boolean.class;
            case ARRAY -> (Class<T>) List.class;
            default -> (Class<T>) String.class;
        };
    }
}