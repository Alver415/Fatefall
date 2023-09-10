package com.alver.fatefall.client;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.app.fx.model.entity.EntityFX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public abstract class EntityClient<T extends EntityFX> implements EntityApi<T> {

	private final WebClient webClient;
	private final String endpoint;
	private final ParameterizedTypeReference<T> type;
	private final ParameterizedTypeReference<List<T>> listType;

	@Value("${web.client.scheme}")
	private String scheme;
	@Value("${web.client.host}")
	private String host;
	@Value("${web.client.port}")
	private String port;

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

	private UriBuilder baseUri(UriBuilder uriBuilder) {
		return uriBuilder.scheme(scheme).host(host).port(port).pathSegment(endpoint);
	}

	@Override
	public List<T> getAll() {
		return webClient.get()
				.uri(uriBuilder -> baseUri(uriBuilder).build())
				.retrieve()
				.bodyToMono(listType)
				.block();
	}

	@Override
	public Optional<T> getById(Long id) {
		return webClient.get()
				.uri(uriBuilder -> baseUri(uriBuilder).pathSegment("{id}").build(id))
				.retrieve()
				.bodyToMono(type)
				.blockOptional();
	}
	@Override
	public T create(T entity) {
		return webClient.post()
				.uri(uriBuilder -> baseUri(uriBuilder).build())
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(type)
				.block();
	}

	@Override
	public T update(Long id, T entity) {
		return webClient.put()
				.uri(uriBuilder -> baseUri(uriBuilder).pathSegment("{id}").build(id))
				.body(Mono.just(entity), entity.getClass())
				.retrieve()
				.bodyToMono(type)
				.block();
	}
	@Override
	public void delete(Long id) {
		webClient.delete()
				.uri(uriBuilder -> baseUri(uriBuilder).pathSegment("{id}").build(id))
				.retrieve()
				.bodyToMono(type)
				.block();
	}
}
