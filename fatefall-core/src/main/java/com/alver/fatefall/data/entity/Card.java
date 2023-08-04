package com.alver.fatefall.data.entity;

public interface Card<ICardFace extends CardFace> extends Entity {

	ICardFace getFront();
	ICardFace getBack();


}
