package com.alver.fatefall.plugin.interfaces;

import com.alver.fatefall.plugin.models.Card;
import javafx.scene.control.MenuItem;

import java.util.Collection;

public interface ContextMenuFactory {

    Collection<MenuItem> buildCardViewMenuItems(Card card);
}
