package com.alver.fatefall.scryfall;

import com.alver.fatefall.fx.core.json.*;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.alver.fatefall.scryfall.api.ScryfallCardDeserializer;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    protected ObjectMapper getObjectMapper(ScryfallCardDeserializer cardDeserializer) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule scryfallModule = new SimpleModule("ScryfallModule");
        scryfallModule.addDeserializer(CardFX.class, cardDeserializer);
        scryfallModule.addDeserializer(SimpleObjectProperty.class, new SimpleObjectPropertyDeserializer());
        scryfallModule.addDeserializer(SimpleListProperty.class, new SimpleListPropertyDeserializer());
        scryfallModule.addDeserializer(SimpleMapProperty.class, new SimpleMapPropertyDeserializer());
        scryfallModule.addDeserializer(TreeProperty.class, new TreePropertyDeserializer());
        scryfallModule.addSerializer(new SimpleListPropertySerializer());
        scryfallModule.addSerializer(new TreePropertySerializer());

        objectMapper.registerModule(scryfallModule);
        return objectMapper;
    }

    @Bean
    @Qualifier("scryfallWebClient")
    protected WebClient getWebClient(ObjectMapper objectMapper) {
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

