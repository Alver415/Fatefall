package com.alver.fatefall.app.plugin.implementations.cardview;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AdjacentSkin extends CardViewSkin {

    protected AdjacentSkin(CardViewImpl control) {
        super(control);
        HBox wrapper = new HBox();
        wrapper.setSpacing(3);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.getChildren().setAll(frontWrapper, backWrapper);
        getChildren().setAll(wrapper);
    }

}
