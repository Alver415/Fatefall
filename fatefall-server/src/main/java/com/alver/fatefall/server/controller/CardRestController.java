package com.alver.fatefall.server.controller;

import com.alver.fatefall.core.api.RequestMappings;
import com.alver.fatefall.core.entity.Card;
import com.alver.fatefall.server.model.CardRow;
import com.alver.fatefall.server.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappings.CARDS)
public class CardRestController extends EntityRestController<Card, CardRow> {

	@Autowired
	public CardRestController(EntityService<Card, CardRow> cardService) {
		super(cardService);
	}
}
