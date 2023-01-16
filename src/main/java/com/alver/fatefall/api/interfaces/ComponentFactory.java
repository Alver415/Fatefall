package com.alver.fatefall.api.interfaces;

public interface ComponentFactory {
    CardCollectionView<?> buildCardCollectionView();

    CardView<?> buildCardView();

}
