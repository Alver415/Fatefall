package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Card;
import javafx.beans.property.ObjectProperty;


public interface CardView extends FxView {

    ObjectProperty<Card> cardProperty();

    default Card getCard(){
        return cardProperty().get();
    }

    default void setCard(Card card){
        cardProperty().set(card);
    }


}
