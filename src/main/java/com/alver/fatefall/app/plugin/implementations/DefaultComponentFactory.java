package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.plugin.implementations.cardcollectionview.DefaultCardCollectionView;
import com.alver.fatefall.app.plugin.implementations.cardview.CardViewImpl;
import com.alver.fatefall.app.services.CardRepository;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultComponentFactory implements ComponentFactory {

    @Autowired
    protected ObservableList<CardCollection> cardCollectionList;
    @Autowired
    protected CardRepository cardRepository;
    @Autowired
    protected BeanFactory beanFactory;

    public MenuItem buildAddToCollectionMenuItem(Card card) {
        Menu menu = new Menu("Add to...");
        cardCollectionList.forEach(c -> {
            MenuItem item = new MenuItem(c.getName());
            item.setOnAction(a -> {
                c.getObservableCards().add(card);
            });
            menu.getItems().add(item);
        });
        return menu;
    }

    public MenuItem buildDeleteCardMenuItem(Card card) {
        MenuItem item = new MenuItem("Delete");
        item.setOnAction(a -> cardCollectionList.stream()
                .map(CardCollection::getObservableCards)
                .forEach(cards -> cards.remove(card)));
        return item;
    }

    public CardView<?> buildCardView(Card card) {
        CardViewImpl cardView = beanFactory.getBean(CardViewImpl.class);
        cardView.setCard(card);
        cardView.getContextMenu().getItems().addAll(buildCardViewContextMenuItems(card));
        return cardView;
    }

    public List<MenuItem> buildCardViewContextMenuItems(Card card) {
        return List.of(
                buildAddToCollectionMenuItem(card),
                buildDeleteCardMenuItem(card));
    }

    public CardCollectionView<?> buildCardCollectionView() {
        return beanFactory.getBean(DefaultCardCollectionView.class);
    }
}
