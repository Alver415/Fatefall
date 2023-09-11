package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.CardRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardRestController extends EntityRestController<Card<?,?>, CardRow> {

	@Autowired
	public CardRestController(EntityService<Card<?, ?>, CardRow> cardService) {
		super(cardService);
	}
}
