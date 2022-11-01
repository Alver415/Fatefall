package com.alver.fatefall.templatebuilder.components.block;

import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DefaultProperty("front")
public class Card extends AnchorPane implements FXMLLoadable {

    public Card() {
        minWidthProperty().bind(cardWidth);
        minHeightProperty().bind(cardHeight);
        maxWidthProperty().bind(cardWidth);
        maxHeightProperty().bind(cardHeight);
        prefWidthProperty().bind(cardWidth);
        prefHeightProperty().bind(cardHeight);

        frontProperty().addListener((observable, oldValue, newValue) -> {
            Rectangle rectangle = new Rectangle();
            rectangle.widthProperty().bind(widthProperty());
            rectangle.heightProperty().bind(heightProperty());
            newValue.setClip(rectangle);
        });
        backProperty().addListener((observable, oldValue, newValue) -> {
            Rectangle rectangle = new Rectangle();
            rectangle.widthProperty().bind(widthProperty());
            rectangle.heightProperty().bind(heightProperty());
            newValue.setClip(rectangle);
        });

        frontProperty().addListener((observable, oldValue, newValue) -> {
            if (getChildren().isEmpty()) {
                getChildren().add(newValue);
            }
        });
        flippedProperty().addListener((observable, oldValue, newValue) -> {
            getChildren().clear();
            if (newValue) {
                getChildren().add(front.get());
            } else {
                getChildren().add(back.get());
            }
        });
        sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null){
                return;
            }
            newValue.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.isControlDown() && event.getCode().equals(KeyCode.F)) {
                    flipped.set(!flipped.get());
                    event.consume();
                }
            });
        });
    }

    public List<? extends Block<?>> getBlocks() {
        return getChildrenRecursive(this).stream()
                .filter(Block.class::isInstance)
                .map(node -> (Block<?>) node)
                .toList();
    }

    public Map<String, Block<?>> getBlockMap() {
        return getChildrenRecursive(this).stream()
                .filter(Block.class::isInstance)
                .map(node -> (Block<?>) node)
                .collect(Collectors.toMap(Block::getId, v -> v));
    }

    public static List<Node> getChildrenRecursive(Parent parent) {
        List<Node> nodes = new ArrayList<>();
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent child) {
                nodes.addAll(getChildrenRecursive(child));
            }
        }
        return nodes;
    }

    protected BooleanProperty flipped = new SimpleBooleanProperty(this, "flipped", false);

    public BooleanProperty flippedProperty() {
        return flipped;
    }

    public boolean getFlipped() {
        return flipped.get();
    }

    public void setFlipped(boolean flipped) {
        this.flipped.set(flipped);
    }


    protected ObjectProperty<CardFace> front = new SimpleObjectProperty<>(this, "front", null);

    public ObjectProperty<CardFace> frontProperty() {
        return front;
    }

    public CardFace getFront() {
        return front.get();
    }

    public void setFront(CardFace front) {
        this.front.set(front);
    }

    protected ObjectProperty<CardFace> back = new SimpleObjectProperty<>(this, "back", null);

    public ObjectProperty<CardFace> backProperty() {
        return back;
    }

    public CardFace getBack() {
        return back.get();
    }

    public void setBack(CardFace Back) {
        this.back.set(Back);
    }

    protected DoubleProperty cardWidth = new SimpleDoubleProperty(this, "cardWidth", 400);

    public DoubleProperty cardWidthProperty() {
        return cardWidth;
    }
    
    public double getCardWidth(){
        return cardWidth.get();
    }
    
    public void setCardWidth(double width){
        this.cardWidth.set(width);
    }

    protected DoubleProperty cardHeight = new SimpleDoubleProperty(this, "cardHeight", 600);

    public DoubleProperty cardHeightProperty() {
        return cardHeight;
    }

    public double getCardHeight(){
        return cardHeight.get();
    }

    public void setCardHeight(double Height){
        this.cardHeight.set(Height);
    }

}
