package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

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
