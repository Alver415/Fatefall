package com.alver.fatefall.api.client;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.models.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    public void delete(Long pk) {
        client.delete()
                .uri("card/" + pk)
                .retrieve()
                .toBodilessEntity()
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
