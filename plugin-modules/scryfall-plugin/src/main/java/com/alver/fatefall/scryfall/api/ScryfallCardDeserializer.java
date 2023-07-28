package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.app.CardDeserializer;
import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.data.entity.Card;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ScryfallCardDeserializer extends CardDeserializer {

    private final String defaultCardBackUrl = Objects.requireNonNull(CardApi.class.getResource("magic_card_back.png")).toExternalForm();

    @Override
    public CardFX buildCard(JsonNode json) {
        CardFX card = super.buildCard(json);
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