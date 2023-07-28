package com.alver.fatefall.client;

import com.alver.fatefall.app.fx.entity.CardFaceFX;
import com.alver.fatefall.data.entity.CardFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CardFaceClient extends EntityClient<CardFaceFX> {

	private static final ParameterizedTypeReference<CardFaceFX> TYPE = new ParameterizedTypeReference<>() {};
	private static final ParameterizedTypeReference<List<CardFaceFX>> LIST_TYPE = new ParameterizedTypeReference<>() {};

	@Autowired
	public CardFaceClient(@Qualifier("fatefallWebClient") WebClient webClient) {
		super(webClient, "card_face", TYPE, LIST_TYPE);
	}
}
