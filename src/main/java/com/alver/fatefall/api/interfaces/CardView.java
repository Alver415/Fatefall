package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Card;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;


public interface CardView<T extends Node> extends FxView<T> {

    ObjectProperty<Card> cardProperty();

    default Card getCard(){
        return cardProperty().get();
    }

    default void setCard(Card card){
        cardProperty().set(card);
    }


}
