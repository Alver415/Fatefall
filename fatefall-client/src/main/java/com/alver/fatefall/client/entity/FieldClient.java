package com.alver.fatefall.client.entity;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class FieldClient extends EntityClient<Field> {

	private static final ParameterizedTypeReference<Field> TYPE = new ParameterizedTypeReference<>() {};
	private static final ParameterizedTypeReference<List<Field>> LIST_TYPE = new ParameterizedTypeReference<>() {};
	@Autowired
	public FieldClient(WebClient webClient) {
		super(webClient, "field", TYPE, LIST_TYPE);
	}
}
