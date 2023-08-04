package com.alver.fatefall.data.entity;

public class CardFaceRow extends EntityRow implements CardFace {

	protected String imageUrl;
	protected String fxmlTemplate;

	@Override
	public String getImageUrl() {
		return imageUrl;
	}
	@Override
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String getFxmlTemplate() {
		return fxmlTemplate;
	}
	@Override
	public void setFxmlTemplate(String fxmlTemplate) {
		this.fxmlTemplate = fxmlTemplate;
	}

}
