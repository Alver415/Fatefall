package com.alver.fatefall.fx.app.view.entity.card.skin;

import com.alver.fatefall.fx.app.view.entity.card.CardView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.SkinBase;

public abstract class AbstractCardViewSkin extends SkinBase<CardView> {

    protected final Node front;
    protected final Node back;

    protected AbstractCardViewSkin(CardView control) {
        super(control);
        this.front = new Group(control.getFront().view());
        this.back = new Group(control.getBack().view());
    }
}
