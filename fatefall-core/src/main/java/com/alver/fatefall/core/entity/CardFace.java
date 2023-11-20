package com.alver.fatefall.core.entity;

public interface CardFace extends Entity {

	Card getCard();
	Template getTemplate();

	enum Face {
		FRONT, BACK
	}
}
