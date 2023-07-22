package com.alver.fatefall.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

	@Bean
	@Qualifier("fatefallWebClient")
	public WebClient getFatefallWebclient() {
		return WebClient.builder()
				.baseUrl("http://localhost:8080")
				.codecs(codecs -> codecs
						.defaultCodecs()
						.maxInMemorySize(500 * 1024))
				.build();
	}
}
