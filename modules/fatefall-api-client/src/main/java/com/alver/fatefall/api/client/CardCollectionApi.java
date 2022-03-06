package com.alver.fatefall.api.client;

import com.alver.fatefall.api.models.CardCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class CardCollectionApi extends AbstractApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardCollectionApi.class);

    protected CardCollectionApi(WebClient client) {
        super(client);
    }


    public List<CardCollection> findAll() {
        return client.get()
                .uri("card_collection")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CardCollection>> (){})
                .block();
    }
    public CardCollection findById(Long id) {
        return client.get()
                .uri("card_collection/" + id)
                .retrieve()
                .bodyToMono(CardCollection.class)
                .block();
    }
}
