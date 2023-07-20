package com.alver.fatefall.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card extends PersistedEntity {

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "template_id")
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
