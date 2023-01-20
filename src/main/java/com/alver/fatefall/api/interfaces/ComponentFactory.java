package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Card;
import javafx.scene.control.MenuItem;

import java.util.List;

public interface ComponentFactory {
    CardCollectionView<?> buildCardCollectionView();

    List<MenuItem> buildCardViewContextMenuItems(Card card);

}
