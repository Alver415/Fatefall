package com.alver.fatefall.app.plugin.interfaces;

import com.alver.fatefall.app.plugin.models.CardCollection;
import javafx.beans.property.ObjectProperty;

public interface CardCollectionView extends FxView{

    ObjectProperty<CardCollection> cardCollectionProperty();

    default CardCollection getCardCollection() {
        return cardCollectionProperty().get();
    }

    default void setCardCollection(CardCollection value) {
        cardCollectionProperty().set(value);
    }

}