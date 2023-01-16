package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.plugin.implementations.cardview.CardFace;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;


public interface CardView<T extends Node> extends FxView<T> {

    ObjectProperty<Card> cardProperty();

    default Card getCard() {
        return cardProperty().get();
    }

    default void setCard(Card card) {
        cardProperty().set(card);
    }


    ObjectProperty<CardFace> frontProperty();

    default CardFace getFront() {
        return frontProperty().get();
    }

    default void setCard(CardFace front) {
        frontProperty().set(front);
    }


    ObjectProperty<CardFace> backProperty();

    default CardFace getBack() {
        return backProperty().get();
    }

    default void setBack(CardFace back) {
        backProperty().set(back);
    }
}
