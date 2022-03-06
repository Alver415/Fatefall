package com.alver.fatefall.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class FatefallApiClient {

    private final WebClient client;

    private final CardApi cardApi;
    private final CardCollectionApi cardCollectionApi;

    public FatefallApiClient(String baseUrl) {
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        this.cardApi = new CardApi(client);
        this.cardCollectionApi = new CardCollectionApi(client);
    }

    public WebClient getClient() {
        return client;
    }
    public CardApi getCardApi() {
        return cardApi;
    }
    public CardCollectionApi getCardCollectionApi() {
        return cardCollectionApi;
    }
}
