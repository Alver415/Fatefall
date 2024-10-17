package com.alver.fatefall.fx.app.rest;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import reactor.netty.http.client.HttpClient;

import javax.annotation.Nonnull;

@Configuration
public class WebClientConfiguration {

	private static final int UNLIMITED = -1;

	private final ObjectMapper objectMapper;
	private final FatefallProperties properties;

	public WebClientConfiguration(ObjectMapper objectMapper, FatefallProperties properties) {
		this.objectMapper = objectMapper;
		this.properties = properties;
	}

	@Bean
	public WebClient getWebClient() {
		return WebClient.builder()
				.uriBuilderFactory(new DefaultUriBuilderFactory() {
					@Override
					@Nonnull
					public UriBuilder builder() {
						return super.builder()
								.scheme(properties.schemeProperty().get())
								.host(properties.hostProperty().get())
								.port(properties.portProperty().get());
					}
				})
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create().wiretap(true)))
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(clientCodecConfigurer -> {
							clientCodecConfigurer.defaultCodecs().jackson2JsonEncoder(
									new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
							clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(
									new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
						}).build())
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(UNLIMITED))
				.build();
	}

}
