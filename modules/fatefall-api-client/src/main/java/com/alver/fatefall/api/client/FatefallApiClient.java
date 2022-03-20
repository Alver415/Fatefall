package com.alver.fatefall.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class FatefallApiClient {

    private final WebClient client;

    private final CardApi cardApi;
    private final CardCollectionApi cardCollectionApi;

    public FatefallApiClient(String baseUrl) {
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(
                        configurer -> configurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE)).build())
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
