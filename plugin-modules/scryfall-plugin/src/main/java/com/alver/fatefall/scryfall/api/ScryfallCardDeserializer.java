package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.CardDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ScryfallCardDeserializer extends CardDeserializer {

    private final String defaultCardBackFaceUrl =
            Objects.requireNonNull(CardApi.class.getResource("magic_card_back.png")).toExternalForm();

    @Autowired
    protected ObjectWriter writer;

    @Override
    protected Card buildCard(ObjectNode json) throws JsonProcessingException {
        Card card = super.buildCard(json);
        card.setName(json.findValue("name").asText());
        if (!json.path("card_faces").isEmpty()) {
            card.setFrontUrl(json.path("card_faces").get(0).path("image_uris").path("normal").asText());
            card.setBackUrl(json.path("card_faces").get(1).path("image_uris").path("normal").asText());
        } else {
            card.setFrontUrl(json.path("image_uris").path("normal").asText());
            card.setBackUrl(defaultCardBackFaceUrl);
        }
        card.setData(writer.writeValueAsString(json));
        return card;
    }

}