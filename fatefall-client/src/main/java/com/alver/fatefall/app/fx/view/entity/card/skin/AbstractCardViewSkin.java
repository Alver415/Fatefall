package com.alver.fatefall.app.fx.view.entity.card.skin;

import com.alver.fatefall.app.fx.view.entity.card.CardView;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;

public abstract class AbstractCardViewSkin extends SkinBase<CardView> {

    protected StackPane frontWrapper;
    protected StackPane backWrapper;

    protected AbstractCardViewSkin(CardView control) {
        super(control);
        frontWrapper = new StackPane(control.getFront());
        backWrapper = new StackPane(control.getBack());
//        frontWrapper.setEffect(properties.getCardFaceShadow());
//        backWrapper.setEffect(properties.getCardFaceShadow());
        frontWrapper.setPickOnBounds(false);
        backWrapper.setPickOnBounds(false);
    }


}
