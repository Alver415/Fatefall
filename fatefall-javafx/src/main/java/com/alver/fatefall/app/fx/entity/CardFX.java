package com.alver.fatefall.app.fx.entity;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.CardFace;
import com.alver.fatefall.data.entity.Workspace;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.Collection;
import java.util.List;

public class CardFX extends EntityFX implements Card<CardFaceFX> {

	private final ObjectProperty<CardFaceFX> front = new SimpleObjectProperty<>(this, "front");
	private final ObjectProperty<CardFaceFX> back = new SimpleObjectProperty<>(this, "back");

	public CardFX() {
		super();
	}
	public CardFX(Long id) {
		super(id);
	}

	public ObjectProperty<CardFaceFX> frontProperty(){
		return front;
	}
	@Override
	public CardFaceFX getFront() {
		return front.get();
	}

	public ObjectProperty<CardFaceFX> backProperty(){
		return back;
	}
	@Override
	public CardFaceFX getBack() {
		return back.get();
	}
}
