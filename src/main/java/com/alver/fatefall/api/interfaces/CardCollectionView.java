package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.CardCollection;
import javafx.beans.property.ObjectProperty;

public interface CardCollectionView extends FxView {

    ObjectProperty<CardCollection> cardCollectionProperty();

    default CardCollection getCardCollection() {
        return cardCollectionProperty().get();
    }

    default void setCardCollection(CardCollection value) {
        cardCollectionProperty().set(value);
    }

}