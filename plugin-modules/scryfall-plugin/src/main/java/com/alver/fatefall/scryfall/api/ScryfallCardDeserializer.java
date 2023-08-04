package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.app.fx.entity.CardFX;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class ScryfallCardDeserializer extends StdDeserializer<CardFX> {

    private final String defaultCardBackUrl = Objects.requireNonNull(CardApi.class.getResource("magic_card_back.png")).toExternalForm();

    public ScryfallCardDeserializer() {
        this(CardFX.class);
    }

    public ScryfallCardDeserializer(Class<?> vc) {
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
        card.setName(json.findValue("name").asText());

        String frontUrl, backUrl;
        if (!json.path("card_faces").isEmpty()) {
            frontUrl = json.path("card_faces").get(0).path("image_uris").path("normal").asText();
            backUrl = json.path("card_faces").get(1).path("image_uris").path("normal").asText();
        } else {
            frontUrl = json.path("image_uris").path("normal").asText();
            backUrl = defaultCardBackUrl;
        }

        card.getFront().setImageUrl(frontUrl);
        card.getBack().setImageUrl(backUrl);

        return card;
    }
}