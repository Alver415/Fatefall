package com.alver.fatefall.server.model;

import com.alver.fatefall.core.entity.Card;
import com.alver.fatefall.core.entity.CardFace;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.HashMap;
import java.util.Map;

@Entity
public class CardRow extends EntityRow implements Card {

	@OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Map<CardFace.Face, CardFaceRow> faces = new HashMap<>(Map.of(
			CardFace.Face.FRONT, new CardFaceRow(),
			CardFace.Face.BACK, new CardFaceRow()));

	@Override
	public CardFaceRow getFront() {
		return faces.get(CardFace.Face.FRONT);
	}
	public void setFront(CardFaceRow front) {
		faces.put(CardFace.Face.FRONT, front);
		front.card = this;
	}

	@Override
	public CardFaceRow getBack() {
		return faces.get(CardFace.Face.BACK);
	}
	public void setBack(CardFaceRow back) {
		faces.put(CardFace.Face.BACK, back);
        back.card = this;
	}
}
