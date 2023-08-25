package com.alver.fatefall.app.fx.view.entity.card.skin.adjacent;

import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.fx.view.entity.card.skin.AbstractCardViewSkin;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AdjacentSkin extends AbstractCardViewSkin {

    public AdjacentSkin(CardView control) {
        super(control);
        HBox wrapper = new HBox();
        wrapper.setSpacing(3);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.getChildren().setAll(frontWrapper, backWrapper);
        getChildren().setAll(wrapper);
    }

}
