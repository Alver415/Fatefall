package com.alver.fatefall.client.entity;

import com.alver.fatefall.data.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CardClient extends EntityClient<Card> {

	@Autowired
	public CardClient(WebClient webClient) {
		super(webClient, "card");
	}
}
