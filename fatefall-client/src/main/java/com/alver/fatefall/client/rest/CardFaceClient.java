package com.alver.fatefall.client.rest;

import com.alver.fatefall.core.api.CardFacesApi;
import com.alver.fatefall.core.api.RequestMappings;
import com.alver.fatefall.core.entity.CardFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class CardFaceClient<T extends CardFace> extends EntityClient<T> implements CardFacesApi<T> {

	@Autowired
	public CardFaceClient(
			WebClient webClient,
			ParameterizedTypeReference<T> type,
			ParameterizedTypeReference<List<T>> listType) {
		super(webClient, RequestMappings.CARD_FACES, type, listType);
	}
}
