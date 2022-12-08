package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.api.models.Card;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CardApi extends AbstractApi {

    public static final String DEFAULT_CARD_BACK_FACE = Objects.requireNonNull(
            CardApi.class.getResource("magic_card_back.png")).toExternalForm();

    protected CardApi(WebClient client) {
        super(client);
    }

    public List<Card> search(String query) {
        JsonNode search = request(query);
        JsonNode data = search.get("data");
        ArrayList<Card> cards = new ArrayList<>();
        for (Iterator<JsonNode> it = data.elements(); it.hasNext(); ) {
            JsonNode element = it.next();
            Card card = new Card();
            if (!element.path("card_faces").isEmpty()) {
                card.setFrontUrl(element.path("card_faces").get(0).path("image_uris").path("normal").asText());
                card.setBackUrl(element.path("card_faces").get(1).path("image_uris").path("normal").asText());
            } else {
                card.setFrontUrl(element.path("image_uris").path("normal").asText());
                card.setBackUrl(DEFAULT_CARD_BACK_FACE);
            }
            card.setData((ObjectNode) element);
            cards.add(card);
        }
        return cards;
    }

    private JsonNode request(String query) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("cards/search")
                        .queryParam("q", query)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }
}
