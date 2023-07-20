package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public abstract class CardViewSkin extends SkinBase<CardViewImpl> {

    protected StackPane frontWrapper;
    protected StackPane backWrapper;

    protected CardViewSkin(CardViewImpl control, FatefallProperties properties) {
        super(control);
        frontWrapper = new StackPane(control.getFront());
        backWrapper = new StackPane(control.getBack());
        frontWrapper.setEffect(properties.getCardFaceShadow());
        backWrapper.setEffect(properties.getCardFaceShadow());
        frontWrapper.setPickOnBounds(false);
        backWrapper.setPickOnBounds(false);
    }


}
