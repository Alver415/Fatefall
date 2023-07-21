package com.alver.fatefall.client.entity;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.data.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public class EntityClient<T extends Entity> implements EntityApi<T> {

	private final WebClient webClient;
	private final String basePath;

	@Autowired
	public EntityClient(WebClient webClient, String basePath) {
		this.webClient = webClient;
		this.basePath = basePath;
	}

	@Override
	public List<T> getAll() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath).build())
				.retrieve()
				.bodyToFlux(new ParameterizedTypeReference<T>() {})
				.collectList()
				.block();
	}
	@Override
	public Optional<T> getById(Long id) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath,"{id}").build(id))
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Optional<T>>() {})
				.block();
	}
	@Override
	public T create(T entity) {
		return webClient.post()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath).build())
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<T>() {})
				.block();
	}

	@Override
	public T update(Long id, T entity) {
		return webClient.put()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath,"{id}").build(id))
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<T>() {})
				.block();
	}
	@Override
	public void delete(Long id) {
		webClient.delete()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath,"{id}").build(id))
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<T>() {})
				.block();
	}
}
