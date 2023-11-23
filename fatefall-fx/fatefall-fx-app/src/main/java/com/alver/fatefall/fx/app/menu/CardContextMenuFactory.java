package com.alver.fatefall.fx.app.menu;

import com.alver.fatefall.core.api.EntityApi;
import com.alver.fatefall.fx.core.model.CardFX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardContextMenuFactory extends EntityContextMenuFactory<CardFX>{

	@Autowired
	public CardContextMenuFactory(EntityApi<CardFX> cardApi) {
		super(cardApi);
	}
}
