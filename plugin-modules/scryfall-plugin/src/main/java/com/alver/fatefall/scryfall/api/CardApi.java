package com.alver.fatefall.scryfall.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CardApi extends AbstractApi {

    @Autowired
    protected ScryfallCardDeserializer cardDeserializer;

    @Autowired
    protected CardApi(WebClient client) {
        super(client);
    }

    public CardApiResult search(String query) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("cards/search")
                        .queryParam("q", query)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CardApiResult.class)
                .block();
    }

}
