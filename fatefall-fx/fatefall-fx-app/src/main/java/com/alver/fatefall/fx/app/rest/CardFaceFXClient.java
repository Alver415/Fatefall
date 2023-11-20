package com.alver.fatefall.fx.app.rest;

import com.alver.fatefall.client.rest.CardFaceClient;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CardFaceFXClient extends CardFaceClient<CardFaceFX> {

	private static final ParameterizedTypeReference<CardFaceFX> TYPE = new ParameterizedTypeReference<>() {
	};
	private static final ParameterizedTypeReference<List<CardFaceFX>> LIST_TYPE = new ParameterizedTypeReference<>() {
	};

	@Autowired
	public CardFaceFXClient(WebClient webClient) {
		super(webClient, TYPE, LIST_TYPE);
	}
}
