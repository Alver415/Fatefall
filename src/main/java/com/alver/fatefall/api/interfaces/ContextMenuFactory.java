package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Card;
import javafx.scene.control.MenuItem;

import java.util.List;

public interface ContextMenuFactory {

    List<MenuItem> buildCardViewMenuItems(Card card);
}
