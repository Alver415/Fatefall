package com.alver.fatefall.json;

import com.alver.fatefall.app.fx.entity.CardFX;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CardFXDeserializer extends StdDeserializer<CardFX> {

    public CardFXDeserializer() {
        this(CardFX.class);
    }

    public CardFXDeserializer(Class<?> vc) {
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
        card.setData(json.toPrettyString());
        if (json.has("name")){
            card.setName(json.get("name").asText());
        } else {
            card.setName("Card [%d]".formatted(card.getData().hashCode()));
        }

        return card;
    }

}