package com.alver.fatefall.client.entity;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.data.entity.Entity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public abstract class EntityClient<T extends Entity> implements EntityApi<T> {

	private final WebClient webClient;
	private final String basePath;
	private final ParameterizedTypeReference<T> type;
	private final ParameterizedTypeReference<List<T>> listType;

	public EntityClient(
			WebClient webClient,
			String basePath,
			ParameterizedTypeReference<T> type,
			ParameterizedTypeReference<List<T>> listType) {
		this.webClient = webClient;
		this.basePath = basePath;
		this.type = type;
		this.listType = listType;
	}

	@Override
	public List<T> getAll() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath).build())
				.retrieve()
				.bodyToMono(listType)
				.block();
	}
	@Override
	public Optional<T> getById(Long id) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath,"{id}").build(id))
				.retrieve()
				.bodyToMono(type)
				.blockOptional();
	}
	@Override
	public T create(T entity) {
		return webClient.post()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath).build())
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(type)
				.block();
	}

	@Override
	public T update(Long id, T entity) {
		return webClient.put()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath,"{id}").build(id))
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(type)
				.block();
	}
	@Override
	public void delete(Long id) {
		webClient.delete()
				.uri(uriBuilder -> uriBuilder.pathSegment(basePath,"{id}").build(id))
				.retrieve()
				.bodyToMono(type)
				.block();
	}
}
