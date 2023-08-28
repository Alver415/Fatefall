package com.alver.fatefall;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.preloader.SplashPreloader;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

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

	@Bean
	public LoadingCache<String, Image> getImageCache() {
		return CacheBuilder.newBuilder().build(new CacheLoader<>() {
			public Image load(String key) { // no checked exception
				return new Image(key, true);
			}
		});
	}

	@Bean
	public ObservableList<WorkspaceFX> getWorkspaces() {
		return FXCollections.observableArrayList();
	}
}