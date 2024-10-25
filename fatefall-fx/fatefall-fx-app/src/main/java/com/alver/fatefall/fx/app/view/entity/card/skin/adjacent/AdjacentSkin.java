package com.alver.fatefall.fx.app.view.entity.card.skin.adjacent;

import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.app.view.entity.card.skin.CardViewSkinBase;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AdjacentSkin extends CardViewSkinBase {

    public AdjacentSkin(CardView control) {
        super(control);
        HBox wrapper = new HBox();
        wrapper.setSpacing(3);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.getChildren().setAll(front, back);
        getChildren().setAll(wrapper);
    }

}
