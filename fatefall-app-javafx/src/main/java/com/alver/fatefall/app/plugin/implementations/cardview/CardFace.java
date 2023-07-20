package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Prototype
public class CardFace extends AnchorPane {

    protected FatefallProperties properties;

    @Autowired
    public CardFace(FatefallProperties properties) {
        super();
        this.properties = properties;

        minWidthProperty().bind(properties.getCardScaledWidth());
        maxWidthProperty().bind(properties.getCardScaledWidth());

        minHeightProperty().bind(properties.getCardScaledHeight());
        maxHeightProperty().bind(properties.getCardScaledHeight());

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(properties.getCardScaledWidth());
        clip.heightProperty().bind(properties.getCardScaledHeight());
        clip.arcWidthProperty().bind(properties.getCardScaledArcWidth());
        clip.arcHeightProperty().bind(properties.getCardScaledArcHeight());
        setClip(clip);
    }
}