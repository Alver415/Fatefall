package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
public class CardFaceView extends AnchorPane {

    protected FatefallProperties properties;

    @Autowired
    public CardFaceView(FatefallProperties properties) {
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
