package com.alver.fatefall.scryfall;

import com.alver.fatefall.app.fx.view.FXMLAutoLoader;
import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.scryfall.api.ScryfallCardDeserializer;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ScryfallConfiguration {

    @Bean
    public FXMLAutoLoader getFXMLAutoLoader() {
        return new FXMLAutoLoader();
    }

    @Bean
    protected ObjectMapper getObjectMapper(ScryfallCardDeserializer cardDeserializer) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Card.class, cardDeserializer);
        objectMapper.registerModule(module);
        return objectMapper;
    }

    @Bean
    @Qualifier("scryfallWebClient")
    protected WebClient getWebClient(ObjectMapper objectMapper, ScryfallCardDeserializer cardDeserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Card.class, cardDeserializer);
        objectMapper.registerModule(module);
        return WebClient.builder()
                .baseUrl("https://api.scryfall.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(
                                configurer -> {
                                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                                    configurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE);
                                })
                        .build())
                .build();
    }

    @Bean
    public Category getScryfallCategory() {
        return Category.of("Scryfall Plugin", Group.of("Greetings from Scryfall"));
    }

}

