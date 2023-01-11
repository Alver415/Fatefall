package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.api.interfaces.ContextMenuFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.services.CardCollectionRepository;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultContextMenuFactory implements ContextMenuFactory {

    @Autowired
    protected CardCollectionRepository cardCollectionRepository;

    public List<MenuItem> buildCardViewMenuItems(Card card) {
        Menu addToCollection = new Menu("Add to...");
        cardCollectionRepository.findAll().forEach(cardCollection -> {
            MenuItem addTo = new MenuItem(cardCollection.getName());
            addTo.setOnAction(a -> {
                cardCollection.getCards().add(card);
                cardCollectionRepository.save(cardCollection);
            });
            addToCollection.getItems().add(addTo);
        });

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(addToCollection);
        return menuItems;
    }
}
