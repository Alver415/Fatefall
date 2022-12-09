package com.alver.fatefall.scryfall;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.FXMLAutoLoader;
import com.alver.fatefall.app.plugin.implementations.cardview.DefaultCardView;
import com.alver.fatefall.scryfall.api.CardApi;
import com.alver.fatefall.scryfall.api.CardDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;
import java.util.Objects;

@SpringBootApplication
public class ScryfallConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DefaultCardView getDefaultCardView() {
        return new DefaultCardView();
    }

    @Bean
    public FXMLAutoLoader getFXMLAutoLoader() {
        return new FXMLAutoLoader();
    }

    @Bean
    public String getDefaultCardBackFaceUrl() {
        URL resource = CardApi.class.getResource("magic_card_back.png");
        return Objects.requireNonNull(resource).toExternalForm();
    }

    @Bean
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
    protected ObjectMapper getObjectMapper(CardDeserializer cardDeserializer){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Card.class, cardDeserializer);
        objectMapper.registerModule(module);
        return objectMapper;
    }

}

