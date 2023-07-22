package com.alver.fatefall.scryfall.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Component
public class CardApi extends AbstractApi {

    @Autowired
    protected CardApi(@Qualifier("scryfallWebClient") WebClient client) {
        super(client);
    }

    public CardApiResult search(String query) {
        return client.get()
                .uri(uriBuilder -> {
                    URI var = uriBuilder.path("cards/search")
                            .queryParam("q", query)
                            .build();
                    return var;
                        }
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CardApiResult.class)
                .block();
    }

}
