package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.springfx.SpringFXBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FatefallSpringFXBuilders {

	@Bean
	public SpringFXBuilder<CardView> getCardViewBuilder(BeanFactory beanFactory) {
		return new SpringFXBuilder<>(CardView.class) {
			@Override
			public CardView build() {
				return beanFactory.getBean(CardView.class);
			}
		};
	}
}
