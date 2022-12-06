package com.alver.fatefall.app.plugin.interfaces;

import com.alver.fatefall.app.plugin.models.Card;
import javafx.scene.control.MenuItem;

import java.util.Collection;

public interface ContextMenuFactory {

    Collection<MenuItem> buildCardViewMenuItems(Card card);
}
