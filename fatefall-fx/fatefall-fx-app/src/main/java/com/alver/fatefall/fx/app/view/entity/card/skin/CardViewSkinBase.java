package com.alver.fatefall.fx.app.view.entity.card.skin;

import com.alver.fatefall.fx.app.view.entity.card.CardView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.SkinBase;

public abstract class CardViewSkinBase extends SkinBase<CardView> {

    protected final Node front;
    protected final Node back;

    protected CardViewSkinBase(CardView control) {
        super(control);
        front = new Group(control.getFront());
        back = new Group(control.getBack());
    }
}
