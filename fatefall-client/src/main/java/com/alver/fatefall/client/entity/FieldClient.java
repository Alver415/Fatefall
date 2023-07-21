package com.alver.fatefall.client.entity;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FieldClient extends EntityClient<Field> {

	@Autowired
	public FieldClient(WebClient webClient) {
		super(webClient, "field");
	}
}
