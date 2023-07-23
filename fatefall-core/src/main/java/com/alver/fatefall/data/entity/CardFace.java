package com.alver.fatefall.data.entity;

public class CardFace extends Entity {

	protected String imageUrl;
	protected String fxmlTemplate;

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFxmlTemplate() {
		return fxmlTemplate;
	}
	public void setFxmlTemplate(String fxmlTemplate) {
		this.fxmlTemplate = fxmlTemplate;
	}

}
