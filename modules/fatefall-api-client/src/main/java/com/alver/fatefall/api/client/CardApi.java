package com.alver.fatefall.api.client;

import com.alver.scryfall.api.models.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

public class CardApi extends AbstractApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardApi.class);

    protected CardApi(WebClient client) {
        super(client);
    }

    public Card findById(Long id) {
        return client.get()
                .uri("card/" + id)
                .retrieve()
                .bodyToMono(Card.class)
                .block();
    }
}
