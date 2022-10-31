package com.alver.scryfall.api;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstractApi {

    protected final WebClient client;

    protected AbstractApi(WebClient client){
        this.client = client;
    }

}
