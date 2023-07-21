package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;

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
