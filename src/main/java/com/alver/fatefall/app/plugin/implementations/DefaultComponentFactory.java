package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.app.plugin.implementations.cardcollectionview.DefaultCardCollectionView;
import com.alver.fatefall.app.plugin.implementations.cardview.DefaultCardView;
import com.alver.fatefall.app.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.app.plugin.interfaces.CardView;
import com.alver.fatefall.app.plugin.interfaces.ComponentFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultComponentFactory implements ComponentFactory {

    @Autowired
    protected BeanFactory beanFactory;

    public CardView buildCardView() {
        return new DefaultCardView();
    }

    public CardCollectionView buildCardCollectionView() {
        return beanFactory.getBean(DefaultCardCollectionView.class);
    }
}
