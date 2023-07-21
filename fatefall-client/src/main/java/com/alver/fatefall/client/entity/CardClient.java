package com.alver.fatefall.client.entity;

import com.alver.fatefall.data.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CardClient extends EntityClient<Card> {

	private static final ParameterizedTypeReference<Card> TYPE = new ParameterizedTypeReference<>() {};
	private static final ParameterizedTypeReference<List<Card>> LIST_TYPE = new ParameterizedTypeReference<>() {};

	@Autowired
	public CardClient(WebClient webClient) {
		super(webClient, "card", TYPE, LIST_TYPE);
	}
}
