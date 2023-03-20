package com.alver.fatefall.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "card")
public class Card extends BaseEntity {

	protected Card template;
	protected String fxml;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "template_id")
	public Card getTemplate() {
		return template;
	}
	public void setTemplate(Card template) {
		this.template = template;
	}

	public String getFxml() {
		// Default to template's fxml if this card doesn't have a custom fxml.
		if (fxml == null && template != null) {
			return template.getFxml();
		}
		return fxml;
	}
	public void setFxml(String fxml) {
		this.fxml = fxml;
	}
}
