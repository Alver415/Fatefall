package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.api.interfaces.ContextMenuFactory;
import com.alver.fatefall.api.models.Card;
import javafx.scene.control.MenuItem;

import java.util.Collection;
import java.util.List;

public class DefaultContextMenuFactory implements ContextMenuFactory {

    public Collection<MenuItem> buildCardViewMenuItems(Card card) {
        return List.of();
    }
}
