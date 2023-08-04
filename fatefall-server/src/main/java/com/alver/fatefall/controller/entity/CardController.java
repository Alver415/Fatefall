package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.CardRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController extends EntityController<Card<?>, CardRow> {

	@Autowired
	public CardController(EntityService<Card<?>, CardRow> cardService) {
		super(cardService);
	}
}
