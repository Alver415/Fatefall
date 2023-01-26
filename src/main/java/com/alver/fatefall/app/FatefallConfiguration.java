package com.alver.fatefall.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javafx.scene.image.Image;
import org.hsqldb.Server;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class FatefallConfiguration implements ApplicationContextAware {

	protected ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}


	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public ObjectWriter getObjectWriter(ObjectMapper objectMapper) {
		return objectMapper.writerWithDefaultPrettyPrinter();
	}

	@Bean
	public LoadingCache<String, Image> getImageCache() {
		return CacheBuilder.newBuilder().build(new CacheLoader<>() {
			public Image load(String key) { // no checked exception
				return new Image(key, true);
			}
		});
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server hsqlServer() {
		Server server = new Server();
		server.setDatabaseName(0, "Fatefall");
		server.setDatabasePath(0, "database/fatefall");
		server.setPort(9001);
		server.setNoSystemExit(true);
		return server;
	}

	@Bean
	@DependsOn("hsqlServer")
	public DataSource getDataSource(@Autowired DataSourceProperties dsProps) {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(dsProps.getDriverClassName());
		dataSourceBuilder.url(dsProps.getUrl());
		dataSourceBuilder.username(dsProps.getUsername());
		dataSourceBuilder.password(dsProps.getPassword());
		return dataSourceBuilder.build();
	}
}