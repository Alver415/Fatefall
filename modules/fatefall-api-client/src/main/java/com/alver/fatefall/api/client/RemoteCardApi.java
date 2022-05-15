package com.alver.fatefall.api.client;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.models.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;

public class RemoteCardApi extends AbstractApi implements CardApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCardApi.class);

    protected RemoteCardApi(WebClient client) {
        super(client);
    }

    public Card findById(Long id) {
        return client.get()
                .uri("card/" + id)
                .retrieve()
                .bodyToMono(Card.class)
                .block();
    }
    public Card save(Card card) {
        return client.put()
                .uri("card")
                .body(Mono.just(card), Card.class)
                .retrieve()
                .bodyToMono(Card.class)
                .block();
    }

    public void delete(Long id) {
        client.delete()
                .uri("card/" + id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public byte[] getImage(String location){
        return client.get()
                .uri(new DefaultUriBuilderFactory().builder()
                        .path("card/image")
                        .queryParam("location", location)
                        .build().toString()
                )
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }

    public Card generateImage(Card card) {
        return client.post()
                .uri("card/generateImage/")
                .body(Mono.just(card), Card.class)
                .retrieve()
                .bodyToMono(Card.class)
                .block();
    }
}
