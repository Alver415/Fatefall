package com.alver.fatefall.app;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.Field;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        Field raw = new Field("_raw_", jsonToString(json));
        card.addField(raw.getName(), raw);

        for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
            String name = it.next();
            JsonNode value = json.get(name);
            Field field = buildAttribute(name, value);
            card.addField(name, field);
        }
        return card;
    }

    protected Field buildAttribute(String name, JsonNode json) {
        Field parentField = new Field(name, json.asText());

        if (json.getNodeType().equals(JsonNodeType.OBJECT)) {
            for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
                String childName = it.next();
                JsonNode childJson = json.get(childName);
                Field childField = buildAttribute(childName, childJson);
                parentField.addField(childField.getName(), childField);
            }
        }
        if (json.getNodeType().equals(JsonNodeType.ARRAY)) {
            int i = 0;
            for (Iterator<JsonNode> it = json.elements(); it.hasNext(); i++) {
                JsonNode childJson = it.next();
                Field childField = buildAttribute("[%d]".formatted(i), childJson);
                parentField.addField(childField.getName(), childField);
            }
        }
        return parentField;
    }

    private String jsonToString(JsonNode json) {
        try {
            return writer.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}