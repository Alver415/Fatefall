package com.alver.fatefall.api.client;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.CardCollectionApi;
import com.alver.fatefall.api.FatefallApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class RemoteFatefallApi implements FatefallApi {

    private final WebClient client;

    private final RemoteCardApi cardApi;
    private final RemoteCardCollectionApi cardCollectionApi;

    public RemoteFatefallApi(String baseUrl) {
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(
                        configurer -> configurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE)).build())
                .build();

        this.cardApi = new RemoteCardApi(client);
        this.cardCollectionApi = new RemoteCardCollectionApi(client);
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
