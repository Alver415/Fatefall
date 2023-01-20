package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
public class CardCollectionViewImpl extends Control implements CardCollectionView<CardCollectionViewImpl> {

	protected final BeanFactory beanFactory;
	protected final FatefallProperties properties;

	protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();

	public ObjectProperty<CardCollection> cardCollectionProperty() {
		return cardCollectionProperty;
	}

	public CardCollectionViewImpl(
			BeanFactory beanFactory,
			FatefallProperties properties) {
		this.beanFactory = beanFactory;
		this.properties = properties;
	}

	@Override
	public Skin<CardCollectionViewImpl> createDefaultSkin() {
		return beanFactory.getBean(CardCollectionSkin.class, this, properties, beanFactory);
	}

}