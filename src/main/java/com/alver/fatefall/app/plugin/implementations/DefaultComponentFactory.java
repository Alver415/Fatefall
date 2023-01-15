package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.plugin.implementations.cardcollectionview.DefaultCardCollectionView;
import com.alver.fatefall.app.plugin.implementations.cardview.AdjacentFacesCardView;
import com.alver.fatefall.app.plugin.implementations.cardview.FlipFacesCardView;
import com.alver.fatefall.app.plugin.implementations.cardview.StackedFacesCardView;
import com.alver.fatefall.app.services.CardRepository;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public FlipFacesCardView buildFlipFacesCardView() {
        FlipFacesCardView cardView = beanFactory.getBean(FlipFacesCardView.class);
        Node node = cardView.getFxViewNode();
        node.setOnContextMenuRequested(e -> {
            ContextMenu contextMenu = buildCardViewContextMenu(cardView.getCard());
            contextMenu.show(node, e.getScreenX(), e.getScreenY());
        });
        return cardView;
    }
    public StackedFacesCardView buildStackedFacesCardView() {
        StackedFacesCardView cardView = beanFactory.getBean(StackedFacesCardView.class);
        Node node = cardView.getFxViewNode();
        node.setOnContextMenuRequested(e -> {
            ContextMenu contextMenu = buildCardViewContextMenu(cardView.getCard());
            contextMenu.show(node, e.getScreenX(), e.getScreenY());
        });
        return cardView;
    }

    public AdjacentFacesCardView buildAdjacentFacesCardView() {
        AdjacentFacesCardView cardView = beanFactory.getBean(AdjacentFacesCardView.class);
        Node node = cardView.getFxViewNode();
        node.setOnContextMenuRequested(e -> {
            ContextMenu contextMenu = buildCardViewContextMenu(cardView.getCard());
            contextMenu.show(node, e.getScreenX(), e.getScreenY());
        });
        return cardView;
    }

    public ContextMenu buildCardViewContextMenu(Card card) {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(buildAddToCollectionMenuItem(card));
        contextMenu.getItems().add(buildDeleteCardMenuItem(card));
        return contextMenu;
    }

    public CardCollectionView<?> buildCardCollectionView() {
        return beanFactory.getBean(DefaultCardCollectionView.class);
    }
}
