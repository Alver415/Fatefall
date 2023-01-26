package com.alver.fatefall.app;

import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.api.models.Card;
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
        card.addAttribute(new Attribute("_raw_", jsonToString(json)));
        for (Iterator<String> it = json.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            JsonNode value = json.get(field);
            card.addAttribute(buildAttribute(field, value));
        }
        return card;
    }

    protected Attribute buildAttribute(String field, JsonNode json) {
        Attribute attribute = new Attribute(field, json.asText());

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

    private String jsonToString(JsonNode json) {
        try {
            return writer.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}