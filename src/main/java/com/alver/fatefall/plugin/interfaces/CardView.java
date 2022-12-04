package com.alver.fatefall.plugin.interfaces;

import com.alver.fatefall.plugin.models.Card;
import javafx.beans.property.ObjectProperty;


public interface CardView extends FxView{

    ObjectProperty<Card> cardProperty();

    default Card getCard(){
        return cardProperty().get();
    }

    default void setCard(Card card){
        cardProperty().set(card);
    }


}
