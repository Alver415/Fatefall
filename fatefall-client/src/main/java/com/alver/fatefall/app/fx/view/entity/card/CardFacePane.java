package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.entity.CardFaceFX;
import com.alver.fatefall.utils.ResourceUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class CardFacePane extends AnchorPane {

    private String CSS = ResourceUtil.css(CardFacePane.class, "CardFacePane.css");

    protected final ObjectProperty<CardFaceFX> cardFaceProperty = new SimpleObjectProperty<>();
    public ObjectProperty<CardFaceFX> cardFaceProperty(){
        return cardFaceProperty;
    }
    public CardFaceFX getCardFace(){
        return cardFaceProperty.get();
    }
    public void setCardFace(CardFaceFX cardFace){
        cardFaceProperty.set(cardFace);
    }

    public CardFacePane(){
        getStylesheets().add(CSS);

        FatefallProperties properties = FatefallProperties.INSTANCE;

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
