package com.alver.fatefall.data.entity;

public class Card extends Entity {

	private String name;

	protected Card template;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Card getTemplate() {
		return template;
	}
	public void setTemplate(Card template) {
		this.template = template;
	}


}
