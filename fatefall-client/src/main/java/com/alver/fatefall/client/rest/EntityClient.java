package com.alver.fatefall.client.rest;

import com.alver.fatefall.core.api.EntityApi;
import com.alver.fatefall.core.entity.Entity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public abstract class EntityClient<T extends Entity> implements EntityApi<T> {

	private final WebClient webClient;
	private final String endpoint;
	private final ParameterizedTypeReference<T> type;
	private final ParameterizedTypeReference<List<T>> listType;

	public EntityClient(
			WebClient webClient,
			String endpoint,
			ParameterizedTypeReference<T> type,
			ParameterizedTypeReference<List<T>> listType) {
		this.webClient = webClient;
		this.endpoint = endpoint;
		this.type = type;
		this.listType = listType;
	}

	@Override
	public List<T> getAll() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.pathSegment(endpoint).build())
				.retrieve()
				.bodyToMono(listType)
				.block();
	}

	@Override
	public Optional<T> getById(Long id) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.pathSegment(endpoint).pathSegment("{id}").build(id))
				.retrieve()
				.bodyToMono(type)
				.blockOptional();
	}
	@Override
	public T create(T entity) {
		return webClient.post()
				.uri(uriBuilder -> uriBuilder.pathSegment(endpoint).build())
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(type)
				.block();
	}

	@Override
	public T update(Long id, T entity) {
		return webClient.put()
				.uri(uriBuilder -> uriBuilder.pathSegment(endpoint).pathSegment("{id}").build(id))
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(type)
				.block();
	}
	@Override
	public void delete(Long id) {
		webClient.delete()
				.uri(uriBuilder -> uriBuilder.pathSegment(endpoint).pathSegment("{id}").build(id))
				.retrieve()
				.bodyToMono(type)
				.block();
	}
}
