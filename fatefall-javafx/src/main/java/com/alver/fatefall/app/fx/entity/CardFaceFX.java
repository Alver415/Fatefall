package com.alver.fatefall.app.fx.entity;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.CardFace;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CardFaceFX extends EntityFX implements CardFace {

	private final StringProperty fxmlTemplate = new SimpleStringProperty(this, "fxmlTemplate");
	private final StringProperty imageUrl = new SimpleStringProperty(this, "imageUrl");

	public CardFaceFX() {
		super();
	}
	public CardFaceFX(Long id) {
		super(id);
	}

	public StringProperty imageUrl(){
		return this.imageUrl;
	}
	@Override
	public String getImageUrl() {
		return this.imageUrl.get();
	}
	@Override
	public void setImageUrl(String imageUrl) {
		this.imageUrl.set(imageUrl);
	}

	public StringProperty fxmlTemplate(){
		return this.fxmlTemplate;
	}
	@Override
	public String getFxmlTemplate() {
		return this.fxmlTemplate.get();
	}
	@Override
	public void setFxmlTemplate(String fxmlTemplate) {
		this.fxmlTemplate.set(fxmlTemplate);

	}
}
