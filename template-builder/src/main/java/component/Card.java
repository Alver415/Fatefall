package component;

import javafx.beans.property.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Card extends AnchorPane implements FXMLLoadable {

    public Card() {
        minWidthProperty().bind(cardWidth);
        minHeightProperty().bind(cardHeight);
        maxWidthProperty().bind(cardWidth);
        maxHeightProperty().bind(cardHeight);
        prefWidthProperty().bind(cardWidth);
        prefHeightProperty().bind(cardHeight);

        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(widthProperty());
        rectangle.heightProperty().bind(heightProperty());
        setClip(rectangle);
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
                if (event.getCode().equals(KeyCode.F)) {
                    flipped.set(!flipped.get());
                }
            });
        });
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

    protected DoubleProperty cardWidth = new SimpleDoubleProperty(this, "cardWidth", 559);

    public DoubleProperty cardWidthProperty() {
        return cardWidth;
    }
    
    public double getCardWidth(){
        return cardWidth.get();
    }
    
    public void setCardWidth(double width){
        this.cardWidth.set(width);
    }

    protected DoubleProperty cardHeight = new SimpleDoubleProperty(this, "cardHeight", 871);

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
