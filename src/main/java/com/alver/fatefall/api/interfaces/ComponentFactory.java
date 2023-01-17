package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Card;

public interface ComponentFactory {
    CardCollectionView<?> buildCardCollectionView();

    CardView<?> buildCardView(Card card);

}
