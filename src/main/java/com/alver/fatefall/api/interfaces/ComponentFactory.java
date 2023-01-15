package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.app.plugin.implementations.cardview.AdjacentFacesCardView;
import com.alver.fatefall.app.plugin.implementations.cardview.FlipFacesCardView;
import com.alver.fatefall.app.plugin.implementations.cardview.StackedFacesCardView;

public interface ComponentFactory {
    CardCollectionView<?> buildCardCollectionView();

    FlipFacesCardView buildFlipFacesCardView();

    StackedFacesCardView buildStackedFacesCardView();

    AdjacentFacesCardView buildAdjacentFacesCardView();

    default CardView<?> buildRandomCardView() {
        int random = ((int) (Math.random() * 3)) % 3;
        return switch (random) {
            case 0:
                yield buildFlipFacesCardView();
            case 1:
                yield buildStackedFacesCardView();
            case 2:
                yield buildAdjacentFacesCardView();
            default:
                throw new IllegalStateException("Unexpected value: " + random);
        };
    }
}
