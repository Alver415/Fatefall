package com.alver.fatefall.api.client;

import com.alver.scryfall.api.models.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public abstract class AbstractApi {

    protected final WebClient client;

    protected AbstractApi(WebClient client){
        this.client = client;
    }

}
