package com.alver.fatefall.fx.app;

import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.fsfx.FileSystemFX;
import com.alver.jfxtra.lib.io.SystemIO;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.List;


@SpringBootApplication
@ComponentScan("com.alver.fatefall")
@PropertySource("classpath:/fatefall-fx-app.properties")
public class FatefallFXApp {

	private static final Logger log = LoggerFactory.getLogger(FatefallFXApp.class);

	public static void main(String... args) {
		com.sun.javafx.util.Logging.getCSSLogger().disableLogging();
		SystemIO.overrideSystemDefaults();
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

	@Bean
	public FileSystemFX getFileSystem() throws IOException {
		return new FileSystemFX();
	}
}