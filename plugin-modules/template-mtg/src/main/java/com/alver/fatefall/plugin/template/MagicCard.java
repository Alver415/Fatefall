package com.alver.fatefall.plugin.template;

import com.alver.fatefall.app.ComponentFactory;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.view.entity.card.CardViewImpl;
import com.google.common.cache.LoadingCache;
import javafx.scene.image.Image;
import org.springframework.beans.factory.BeanFactory;

public class MagicCard extends CardViewImpl {

    public MagicCard(BeanFactory beanFactory, ComponentFactory componentFactory, LoadingCache<String, Image> imageCache, FatefallProperties properties) {
        super(beanFactory, componentFactory, imageCache, properties);
    }
}