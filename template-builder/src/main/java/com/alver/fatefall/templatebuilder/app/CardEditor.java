package com.alver.fatefall.templatebuilder.app;

import com.alver.aspect.fxml.FXMLComponent;
import com.alver.fatefall.templatebuilder.components.block.Card;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@FXMLComponent
public class CardEditor extends StackPane implements FXMLLoadable {

    @FXML
    protected StackPane wrapper;

    public CardEditor(){
        wrapper.getChildren().addListener((ListChangeListener<? super Node>) observable -> {
            if (observable.getList().size() > 0 ){
                setCard((Card) observable.getList().get(0));
            }
        });

        addEventFilter(MouseEvent.MOUSE_CLICKED, e -> requestFocus());
        addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.isControlDown() && e.getCode().equals(KeyCode.O)) {
                toggleShowOutlines();
            } else if (e.isControlDown() && e.getCode().equals(KeyCode.P)) {
                screenshot();
            } else if (e.isControlDown() && e.getCode().equals(KeyCode.R)) {
                resetTransform();
            }
        });
        cardProperty().addListener((observable, oldValue, newValue) -> {
            wrapper.getChildren().clear();
            wrapper.getChildren().add(newValue);
        });

        showOutlinesProperty().addListener(observable -> {
            if (showOutlinesProperty().get()) {
                getStyleClass().add("outlined");
            } else {
                getStyleClass().removeAll("outlined");
            }
        });

        addEventFilter(ScrollEvent.SCROLL, event -> {
            double deltaX = event.getDeltaX();
            double deltaY = event.getDeltaY();
            if (event.isControlDown()) {
                DoubleProperty x = wrapper.translateXProperty();
                DoubleProperty y = wrapper.translateYProperty();
                x.set(x.get() + deltaX);
                y.set(y.get() + deltaY);
            } else {
                DoubleProperty scaleX = wrapper.scaleXProperty();
                DoubleProperty scaleY = wrapper.scaleYProperty();
                double newScale = scaleX.get() + deltaY / 100d;
                newScale = Math.min(Math.max(0.1, newScale), 10.0);
                scaleX.set(newScale);
                scaleY.set(newScale);

                wrapper.setRotationAxis(new Point3D(0, 1, 0));
                DoubleProperty rotation = wrapper.rotateProperty();
                double newRotation = rotation.get() + deltaX / 10d;
                newRotation = Math.floorMod((int) newRotation, 360);
                rotation.set(newRotation);

                getCard().setFlipped(newRotation < 90 || newRotation > 270);
            }
        });
    }

    public void resetTransform() {
        wrapper.setTranslateX(0);
        wrapper.setTranslateY(0);
        wrapper.setTranslateZ(0);
        wrapper.setScaleX(1);
        wrapper.setScaleY(1);
        wrapper.setScaleZ(1);
        wrapper.setRotate(0);
        getCard().setFlipped(false);
    }

    private void toggleShowOutlines() {
        setShowOutlines(!getShowOutlines());
    }

    private void screenshot() {
        boolean before = getShowOutlines();
        setShowOutlines(false);
        WritableImage snapshot = getCard().snapshot(new SnapshotParameters(), null);
        setShowOutlines(before);
        Scene scene = new Scene(new BorderPane(new ImageView(snapshot)));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public BooleanProperty showOutlines = new SimpleBooleanProperty(this, "showOutlines", true);

    public BooleanProperty showOutlinesProperty() {
        return showOutlines;
    }

    public boolean getShowOutlines() {
        return showOutlinesProperty().get();
    }

    public void setShowOutlines(boolean showOutlines) {
        showOutlinesProperty().set(showOutlines);
    }

    protected ObjectProperty<Card> card = new SimpleObjectProperty<>(this, "card", null);

    public ObjectProperty<Card> cardProperty() {
        return card;
    }

    public Card getCard() {
        return card.get();
    }

    public void setCard(Card card) {
        this.card.set(card);
    }

}
