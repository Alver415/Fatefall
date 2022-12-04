package com.alver.fatefall.plugin.implementations;

import com.alver.fatefall.plugin.interfaces.ContextMenuFactory;
import com.alver.fatefall.plugin.models.Card;
import javafx.scene.control.MenuItem;

import java.util.Collection;
import java.util.List;

public class DefaultContextMenuFactory implements ContextMenuFactory {

    public Collection<MenuItem> buildCardViewMenuItems(Card card) {
        return List.of();
    }
}
