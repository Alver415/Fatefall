package com.alver.fatefall.app;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardAttribute;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang.NotImplementedException;
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
        Card card = new Card();
        ObjectNode json = parser.getCodec().readTree(parser);

        for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            JsonNode value = json.get(field);
            CardAttribute<?> attribute = buildAttribute(field, value);
            card.getAttributeList().add(attribute);
        }

        card.setData(writer.writeValueAsString(json));
        return card;
    }

    protected CardAttribute<?> buildAttribute(String field, JsonNode json) {
        CardAttribute<?> attribute = new CardAttribute<>();
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
        } else if (json.getNodeType().equals(JsonNodeType.ARRAY)){
            attribute.set("list");
        } else {
            attribute.set(json.asText());
        }
        return attribute;
    }

    @SuppressWarnings("unchecked")
    protected <T> Class<T> convertType(JsonNodeType nodeType) {
        return switch (nodeType) {
            case STRING, NULL -> (Class<T>) String.class;
            case NUMBER -> (Class<T>) Double.class;
            case BOOLEAN -> (Class<T>) Boolean.class;
            case OBJECT -> (Class<T>) CardAttribute.class;
            case ARRAY -> (Class<T>) List.class;
            default -> throw new NotImplementedException("Unexpected value: " + nodeType);
        };
    }
}