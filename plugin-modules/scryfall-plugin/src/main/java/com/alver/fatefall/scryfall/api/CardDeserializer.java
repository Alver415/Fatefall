package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.api.models.Card;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CardDeserializer extends StdDeserializer<Card> {

    @Autowired
    protected String defaultCardBackFaceUrl;

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
        ObjectNode source = parser.getCodec().readTree(parser);
        if (!source.path("card_faces").isEmpty()) {
            card.setFrontUrl(source.path("card_faces").get(0).path("image_uris").path("normal").asText());
            card.setBackUrl(source.path("card_faces").get(1).path("image_uris").path("normal").asText());
        } else {
            card.setFrontUrl(source.path("image_uris").path("normal").asText());
            card.setBackUrl(defaultCardBackFaceUrl);
        }
        card.setData(source);
        return card;
    }
}