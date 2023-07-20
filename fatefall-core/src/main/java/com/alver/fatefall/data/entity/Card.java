package com.alver.fatefall.data.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
