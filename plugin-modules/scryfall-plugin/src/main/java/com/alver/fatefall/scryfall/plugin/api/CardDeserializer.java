package com.alver.fatefall.scryfall.plugin.api;

import com.alver.fatefall.app.plugin.models.Card;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class CardDeserializer extends StdDeserializer<Card> {

    public CardDeserializer() {
        this(null);
    }

    public CardDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Card deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        Card card = new Card();
        ObjectNode data = parser.getCodec().readTree(parser);
        ArrayNode card_faces = (ArrayNode) data.get("card_faces");
        if (card_faces != null) {
            String frontFaceUrl = card_faces.get(0).path("image_uris").path("normal").asText();
            String backFaceUrl = card_faces.get(1).path("image_uris").path("normal").asText();
            card.setFrontUrl(frontFaceUrl);
            card.setBackUrl(backFaceUrl);
        } else {
            String frontFaceUrl = data.path("image_uris").path("normal").asText();
            card.setBackUrl(frontFaceUrl);
        }
        card.setData(data);
        return card;
    }
}