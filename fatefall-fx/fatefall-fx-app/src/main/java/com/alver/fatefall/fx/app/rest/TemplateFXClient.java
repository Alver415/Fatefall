package com.alver.fatefall.fx.app.rest;

import com.alver.fatefall.client.rest.TemplateClient;
import com.alver.fatefall.fx.core.model.TemplateFX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class TemplateFXClient extends TemplateClient<TemplateFX> {

	private static final ParameterizedTypeReference<TemplateFX> TYPE = new ParameterizedTypeReference<>() {
	};
	private static final ParameterizedTypeReference<List<TemplateFX>> LIST_TYPE = new ParameterizedTypeReference<>() {
	};

	@Autowired
	public TemplateFXClient(WebClient webClient) {
		super(webClient, TYPE, LIST_TYPE);
	}
}
