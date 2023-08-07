package com.alver.fatefall;

import com.alver.fatefall.preloader.SplashPreloader;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.List;

@SpringBootApplication
@PropertySource("classpath:/fatefall-client.properties")
public class FatefallClientApplication {

	public static void main(String... args) {
		System.setProperty("javafx.preloader", SplashPreloader.class.getCanonicalName());
		Application.launch(FatefallFXApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper(List<Module> modules) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModules(modules);
		return objectMapper;
	}

}