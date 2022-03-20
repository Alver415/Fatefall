package com.alver.scryfall.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class ScryfallApiClient {

    private final WebClient client;
    private final CardApi cardApi;

    public ScryfallApiClient() {
        this("https://api.scryfall.com");
    }
    public ScryfallApiClient(String baseUrl) {
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(
                        configurer -> configurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE)).build())
                .build();

        this.cardApi = new CardApi(client);
    }

    public WebClient getClient() {
        return client;
    }
    public CardApi getCardApi() {
        return cardApi;
    }
}
