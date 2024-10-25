package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.springfx.annotations.Prototype;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardViewConfiguration {


	@Prototype
	public CardView buildCardView(FatefallProperties properties) {
		CardView cardView = new CardView();

		properties.getCardViewSkinSelection()
				.map(cardView::buildCardViewSkin)
				.subscribe(cardView::setSkin);

		return cardView;
	}

}
