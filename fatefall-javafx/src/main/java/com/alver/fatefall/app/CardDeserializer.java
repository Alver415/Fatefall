package com.alver.fatefall.app;

import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.data.entity.Card;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

@Component
public class CardDeserializer extends StdDeserializer<CardFX> {

    @Autowired
    protected ObjectWriter writer;

    public CardDeserializer() {
        this(Card.class);
    }

    public CardDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CardFX deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        JsonNode json = parser.getCodec().readTree(parser);
        return buildCard(json);
    }

    public CardFX buildCard(JsonNode json) {
        CardFX card = new CardFX();
        card.setData(writeValueAsString(json));
        if (json.has("name")){
            card.setName(json.get("name").asText());
        } else {
            card.setName("Card [%d]".formatted(card.getData().hashCode()));
        }

        return card;
    }

    private String writeValueAsString(JsonNode json) {
        try {
            return writer.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}