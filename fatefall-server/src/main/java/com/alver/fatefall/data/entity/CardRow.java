package com.alver.fatefall.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.HashMap;
import java.util.Map;

@Entity
public class CardRow extends EntityRow implements Card<CardFaceRow, TemplateRow> {

	@OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Map<Face, CardFaceRow> faces = new HashMap<>(Map.of(
			Face.FRONT, new CardFaceRow(),
			Face.BACK, new CardFaceRow()));

	@Override
	public CardFaceRow getFront() {
		return faces.get(Face.FRONT);
	}
	public void setFront(CardFaceRow front) {
		faces.put(Face.FRONT, front);
		front.card = this;
	}

	@Override
	public CardFaceRow getBack() {
		return faces.get(Face.BACK);
	}
	public void setBack(CardFaceRow back) {
		faces.put(Face.BACK, back);
        back.card = this;
	}
}
