package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Card;
import javafx.scene.control.MenuItem;

import java.util.Collection;

public interface ContextMenuFactory {

    Collection<MenuItem> buildCardViewMenuItems(Card card);
}
