package com.alver.fatefall.plugin.implementations;

import com.alver.fatefall.plugin.implementations.cardcollectionview.DefaultCardCollectionView;
import com.alver.fatefall.plugin.implementations.cardview.DefaultCardView;
import com.alver.fatefall.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.plugin.interfaces.CardView;
import com.alver.fatefall.plugin.interfaces.ComponentFactory;

public class DefaultComponentFactory implements ComponentFactory {
    public CardView buildCardView(){
        return new DefaultCardView();
    };

    public CardCollectionView buildCardCollectionView(){
        return new DefaultCardCollectionView();
    }
}
