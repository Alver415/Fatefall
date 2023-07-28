package com.alver.fatefall.client;

import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.data.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CardClient extends EntityClient<CardFX> {

	private static final ParameterizedTypeReference<CardFX> TYPE = new ParameterizedTypeReference<>() {};
	private static final ParameterizedTypeReference<List<CardFX>> LIST_TYPE = new ParameterizedTypeReference<>() {};

	@Autowired
	public CardClient(@Qualifier("fatefallWebClient") WebClient webClient) {
		super(webClient, "card", TYPE, LIST_TYPE);
	}
}
