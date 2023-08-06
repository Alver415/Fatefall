package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.app.fx.view.FxView;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Skinnable;

public interface CardView<T extends Node> extends FxView<T>, Skinnable {

    ObjectProperty<CardFX> cardProperty();

    default CardFX getCard() {
        return cardProperty().get();
    }

    default void setCard(CardFX card) {
        cardProperty().set(card);
    }


    ObjectProperty<CardFaceView> frontProperty();

    default CardFaceView getFront() {
        return frontProperty().get();
    }

    default void setFront(CardFaceView front) {
        frontProperty().set(front);
    }


    ObjectProperty<CardFaceView> backProperty();

    default CardFaceView getBack() {
        return backProperty().get();
    }

    default void setBack(CardFaceView back) {
        backProperty().set(back);
    }
}