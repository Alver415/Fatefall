package com.alver.fatefall.app.fx.view.entity.card.face;

import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.model.entity.CardFaceFX;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@FXMLPrototype
public class CardFaceController {
    private static final Logger log = LoggerFactory.getLogger(CardFaceController.class);

    @FXML
    private Pane root;

    protected final ObjectProperty<CardFaceFX> cardFaceProperty = new SimpleObjectProperty<>();
    protected final DoubleProperty widthProperty = new SimpleDoubleProperty(250);
    protected final DoubleProperty heightProperty = new SimpleDoubleProperty(350);
    protected final DoubleProperty arcWidthProperty = new SimpleDoubleProperty(25);
    protected final DoubleProperty arcHeightProperty = new SimpleDoubleProperty(25);

    private final SpringFXLoader loader;

    @Autowired
    public CardFaceController(SpringFXLoader loader, FatefallProperties properties) {
        this.loader = loader;

        widthProperty.bind(properties.getCardBaseWidth());
        heightProperty.bind(properties.getCardBaseHeight());
        arcWidthProperty.bind(properties.getCardBaseArcWidth());
        arcHeightProperty.bind(properties.getCardBaseArcHeight());
    }

    public void initialize() {
        cardFaceProperty.addListener((observable, oldValue, newValue) -> {
            try {
                String imageUrl = newValue.getTemplate().getImageUrl();
                String fxmlUrl = newValue.getTemplate().getFxmlUrl();
                URL fxml = fxmlUrl != null ? new URL(fxmlUrl) :
                        imageUrl != null ? CardFaceController.class.getResource("ImageTemplate.fxml") :
                                CardFaceController.class.getResource("PlaceholderTemplate.fxml");
                loader.setLocation(fxml);
                Node faceNode = loader.load();
                TemplateController controller = loader.getController();
                controller.imageProperty.set(imageUrl == null ? null : new Image(imageUrl));
                root.getChildren().setAll(faceNode);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
        cardFaceProperty.set(null);
    }


    public ObjectProperty<CardFaceFX> cardFaceProperty() {
        return cardFaceProperty;
    }

    public CardFaceFX getCardFace() {
        return cardFaceProperty.get();
    }

    public void setCardFace(CardFaceFX cardFace) {
        cardFaceProperty.set(cardFace);
    }

    public double getWidth() {
        return widthProperty.get();
    }

    public DoubleProperty widthProperty() {
        return widthProperty;
    }

    public void setWidth(double widthProperty) {
        this.widthProperty.set(widthProperty);
    }

    public double getHeight() {
        return heightProperty.get();
    }

    public DoubleProperty heightProperty() {
        return heightProperty;
    }

    public void setHeight(double heightProperty) {
        this.heightProperty.set(heightProperty);
    }

    public double getArcWidth() {
        return arcWidthProperty.get();
    }

    public DoubleProperty arcWidthProperty() {
        return arcWidthProperty;
    }

    public void setArcWidth(double arcWidthProperty) {
        this.arcWidthProperty.set(arcWidthProperty);
    }

    public double getArcHeight() {
        return arcHeightProperty.get();
    }

    public DoubleProperty arcHeightProperty() {
        return arcHeightProperty;
    }

    public void setArcHeight(double arcHeightProperty) {
        this.arcHeightProperty.set(arcHeightProperty);
    }

}
