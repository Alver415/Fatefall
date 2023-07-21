package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
public class AdjacentSkin extends CardViewSkin {

    @Autowired
    protected AdjacentSkin(CardViewImpl control, FatefallProperties properties) {
        super(control, properties);
        HBox wrapper = new HBox();
        wrapper.setSpacing(3);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.getChildren().setAll(frontWrapper, backWrapper);
        getChildren().setAll(wrapper);
    }

}
