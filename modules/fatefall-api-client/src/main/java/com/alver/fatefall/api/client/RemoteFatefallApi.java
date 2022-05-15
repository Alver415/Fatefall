package com.alver.fatefall.api.client;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.CardCollectionApi;
import com.alver.fatefall.api.CardImageApi;
import com.alver.fatefall.api.FatefallApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

public class RemoteFatefallApi implements FatefallApi {

    private final RemoteCardApi cardApi;
    private final RemoteCardImageApi cardImageApi;
    private final RemoteCardCollectionApi cardCollectionApi;

    public RemoteFatefallApi(String baseUrl) {
        this(WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(
                        configurer -> configurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE)).build())
                .build()
        );
    }

    public RemoteFatefallApi(WebClient webClient){
        this.cardApi = new RemoteCardApi(webClient);
        this.cardImageApi = new RemoteCardImageApi(webClient);
        this.cardCollectionApi = new RemoteCardCollectionApi(webClient);
    }

    public CardApi getCardApi() {
        return cardApi;
    }
    public CardImageApi getCardImageApi() {
        return cardImageApi;
    }
    public CardCollectionApi getCardCollectionApi() {
        return cardCollectionApi;
    }
}
