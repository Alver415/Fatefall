package com.alver.fatefall.app.plugin.implementations.cardview;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;

public abstract class CardViewSkin extends SkinBase<CardViewImpl> {

    protected StackPane frontWrapper;
    protected StackPane backWrapper;

    protected CardViewSkin(CardViewImpl control) {
        super(control);
        frontWrapper = new StackPane(control.getFront());
        backWrapper = new StackPane(control.getBack());
    }
}
