package com.alver.fatefall.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient getFatefallWebclient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> {
                            clientCodecConfigurer.defaultCodecs().jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                            clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(
                                    new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                        }).build())
                .codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(500 * 1024))
                .build();
    }
}
