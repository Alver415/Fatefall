package com.alver.fatefall.scryfall.plugin.component;


import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import com.alver.fatefall.app.plugin.implementations.DefaultComponentFactory;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ScryfallComponentFactory extends DefaultComponentFactory {

    @Autowired
    @Lazy
    protected ApplicationView applicationView;
    @Autowired
    @Lazy
    protected ScryfallContextMenuFactory contextMenuFactory;

    @Override
    public CardView buildCardView() {
        CardView cardView = super.buildCardView();
        cardView.getFxViewNode().setOnContextMenuRequested(e -> {
            Collection<MenuItem> menuItems = contextMenuFactory.buildCardViewMenuItems(cardView.getCard());
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.getItems().addAll(menuItems);
            contextMenu.show(cardView.getFxViewNode(), Side.RIGHT, 0, 0);
        });
        return cardView;
    }

    @Override
    public CardCollectionView buildCardCollectionView() {
        return new ScryfallSearchView();
    }
}
