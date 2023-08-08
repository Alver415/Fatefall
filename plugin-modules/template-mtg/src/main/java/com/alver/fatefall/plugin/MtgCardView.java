package com.alver.fatefall.plugin;

import com.alver.fatefall.app.ComponentFactory;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.view.entity.card.CardViewImpl;
import com.google.common.cache.LoadingCache;
import javafx.scene.image.Image;
import org.springframework.beans.factory.BeanFactory;

public class MtgCardView extends CardViewImpl {

	public MtgCardView(BeanFactory beanFactory, ComponentFactory componentFactory, LoadingCache<String, Image> imageCache, FatefallProperties properties) {
		super(beanFactory, componentFactory, imageCache, properties);
	}

}
