package components;

import app.CardEditor;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public abstract class BaseComponent extends AnchorPane {
    public static final String STYLE_CLASS = "component";
    public static final Insets ZERO_INSETS = new Insets(0);

    public BaseComponent() {
        super();
        setPadding(ZERO_INSETS);
        getStyleClass().add(STYLE_CLASS);

        setupTooltip();
        setupDragListener();
    }

    private void setupTooltip() {
        Tooltip tooltip = new Tooltip();
        tooltip.textProperty().bind(idProperty());
        tooltip.setShowDelay(Duration.ZERO);
        Tooltip.install(this, tooltip);
    }

    private void setupDragListener() {
        addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            private volatile double screenX = 0;
            private volatile double screenY = 0;
            private volatile double myX = 0;
            private volatile double myY = 0;

            @Override
            public void handle(MouseEvent e) {
                if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    screenX = e.getScreenX();
                    screenY = e.getScreenY();
                    myX = getTranslateX();
                    myY = getTranslateY();
                }
                if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
                    double deltaX = e.getScreenX() - screenX;
                    double deltaY = e.getScreenY() - screenY;
                    setTranslateX(myX + deltaX);
                    setTranslateY(myY + deltaY);
                }
            }
        });
    }

    protected DoubleProperty top = new SimpleDoubleProperty(this, "top", 0);

    public DoubleProperty topProperty() {
        return top;
    }

    public Double getTop() {
        return AnchorPane.getTopAnchor(this);
    }

    public void setTop(Double top) {
        AnchorPane.setTopAnchor(this, top);
    }

    protected DoubleProperty right = new SimpleDoubleProperty(this, "right", 0);

    public DoubleProperty rightProperty() {
        return right;
    }

    public Double getRight() {
        return AnchorPane.getRightAnchor(this);
    }

    public void setRight(Double right) {
        AnchorPane.setRightAnchor(this, right);
    }

    protected DoubleProperty bottom = new SimpleDoubleProperty(this, "bottom", 0);

    public DoubleProperty bottomProperty() {
        return bottom;
    }

    public Double getBottom() {
        return AnchorPane.getBottomAnchor(this);
    }

    public void setBottom(Double bottom) {
        AnchorPane.setBottomAnchor(this, bottom);
    }

    protected DoubleProperty left = new SimpleDoubleProperty(this, "left", 0);

    public DoubleProperty leftProperty() {
        return left;
    }

    public Double getLeft() {
        return AnchorPane.getLeftAnchor(this);
    }

    public void setLeft(Double left) {
        AnchorPane.setLeftAnchor(this, left);
    }

    public StringProperty value = new SimpleStringProperty(this, "value", "");

    public StringProperty valueProperty() {
        return value;
    }

    public String getValue() {
        return this.value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public ObjectProperty<CardEditor> card = new SimpleObjectProperty<>(this, "card", null) {
        {
            this.addListener((observable, oldValue, newValue) -> {
                StringProperty property = newValue.data.getOrDefault(idProperty(), new SimpleStringProperty(card, getId(), ""));
                newValue.data.put(idProperty(), property);
                //Order matters here. Uses 'value' instead of 'property'.
                property.bindBidirectional(value);
            });
        }
    };

    public ObjectProperty<CardEditor> cardProperty() {
        return card;
    }

    public void setCard(CardEditor card) {
        this.card.set(card);
    }

    public CardEditor getCard() {
        return card.get();
    }
}
