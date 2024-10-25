package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.springfx.annotations.Prototype;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardFaceViewConfiguration {

	@Prototype
	public CardFaceView buildCardFaceView() {
		return new CardFaceView();
	}
}
