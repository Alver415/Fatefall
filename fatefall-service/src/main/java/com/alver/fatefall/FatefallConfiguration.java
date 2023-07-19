package com.alver.fatefall;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FatefallConfiguration {

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public ObjectWriter getObjectWriter(ObjectMapper objectMapper) {
		return objectMapper.writerWithDefaultPrettyPrinter();
	}
}