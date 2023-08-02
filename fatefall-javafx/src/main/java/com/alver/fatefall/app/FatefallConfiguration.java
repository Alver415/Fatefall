package com.alver.fatefall.app;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FatefallConfiguration {

	@Bean
	public ObservableList<WorkspaceFX> getWorkspaces() {
		return FXCollections.observableArrayList();
	}

	@Bean
	public LoadingCache<String, Image> getImageCache() {
		return CacheBuilder.newBuilder().build(new CacheLoader<>() {
			public Image load(String key) { // no checked exception
				return new Image(key, true);
			}
		});
	}

}