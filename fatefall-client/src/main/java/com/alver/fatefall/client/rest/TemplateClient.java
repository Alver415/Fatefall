package com.alver.fatefall.client.rest;

import com.alver.fatefall.core.api.RequestMappings;
import com.alver.fatefall.core.api.TemplatesApi;
import com.alver.fatefall.core.entity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class TemplateClient<T extends Template> extends EntityClient<T> implements TemplatesApi<T> {

	@Autowired
	public TemplateClient(
			WebClient webClient,
			ParameterizedTypeReference<T> type,
			ParameterizedTypeReference<List<T>> listType) {
		super(webClient, RequestMappings.TEMPLATES, type, listType);
	}
}
