package com.alver.fatefall.api.client;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.CardCollectionApi;
import com.alver.fatefall.api.FatefallApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class FatefallApiClient implements FatefallApi {

    private final WebClient client;

    private final CardApiClient cardApi;
    private final CardCollectionApiClient cardCollectionApi;

    public FatefallApiClient(String baseUrl) {
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(
                        configurer -> configurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE)).build())
                .build();

        this.cardApi = new CardApiClient(client);
        this.cardCollectionApi = new CardCollectionApiClient(client);
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
