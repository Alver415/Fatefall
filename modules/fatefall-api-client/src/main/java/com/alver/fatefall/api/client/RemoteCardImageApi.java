package com.alver.fatefall.api.client;

import com.alver.fatefall.api.CardImageApi;
import com.alver.fatefall.api.models.CardImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RemoteCardImageApi extends AbstractApi implements CardImageApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCardImageApi.class);

    protected RemoteCardImageApi(WebClient client) {
        super(client);
    }

    public byte[] downloadImage(Long id) {
        return client.get()
                .uri("card_image/" + id)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }

}
