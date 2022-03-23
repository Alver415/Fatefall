package com.alver.fatefall.api.client;

import com.alver.fatefall.api.models.CardCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class CardCollectionApi extends AbstractApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardCollectionApi.class);

    protected CardCollectionApi(WebClient client) {
        super(client);
    }

    public List<CardCollection> findAll() {
        return findAllRequest().block();
    }

    public Disposable findAllThen(Consumer<List<CardCollection>> consumer) {
        return findAllRequest().subscribe(consumer);
    }

    private Mono<List<CardCollection>> findAllRequest() {
        return client.get()
                .uri("card_collection")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CardCollection>>() {});
    }

    public CardCollection findById(Long id) {
        return client.get()
                .uri("card_collection/" + id)
                .retrieve()
                .bodyToMono(CardCollection.class)
                .block();
    }
    public CardCollection save(CardCollection cardCollection) {
        return client.put()
                .uri("card_collection")
                .body(Mono.just(cardCollection), CardCollection.class)
                .retrieve()
                .bodyToMono(CardCollection.class)
                .block();
    }

    public CardCollection importFromMse(String name, File mseSet) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("name", name);
        builder.part("filename", mseSet.getName());
        builder.part("file", new FileSystemResource((mseSet)));

        return client.post()
                .uri("card_collection/importFromMse")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(CardCollection.class)
                .block();
    }
}
