package components;

import app.Bindingz;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.lang.reflect.Method;

public abstract class BaseComponent extends AnchorPane {
    public static final String STYLE_CLASS = "component";

    public BaseComponent() {
        super();
        getStyleClass().add(STYLE_CLASS);

        setupTooltip();
        setupDragListener();

        setOnMouseClicked(event -> {
            selected.set(!selected.get());
        });

        double INVERTED = -1d;
        Bindingz.bindBidirectional(viewOrderProperty(), translateZProperty(), INVERTED);
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

    public class AnchorProperty extends SimpleDoubleProperty {
        Method getMethod;
        Method setMethod;

        public AnchorProperty(Object bean, String name, double initialValue) {
            super(bean, name, initialValue);
            try {
                String getMethodName = switch (name) {
                    case "top" -> "getTopAnchor";
                    case "right" -> "getRightAnchor";
                    case "bottom" -> "getBottomAnchor";
                    case "left" -> "getLeftAnchor";
                    default -> throw new RuntimeException("Invalid name: " + name);
                };
                String setMethodName = switch (name) {
                    case "top" -> "setTopAnchor";
                    case "right" -> "setRightAnchor";
                    case "bottom" -> "setBottomAnchor";
                    case "left" -> "setLeftAnchor";
                    default -> throw new RuntimeException("Invalid name: " + name);
                };
                getMethod = AnchorPane.class.getDeclaredMethod(getMethodName, Node.class);
                setMethod = AnchorPane.class.getDeclaredMethod(setMethodName, Node.class, Double.class);
                set(initialValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void _set(Double value) {
            try {
                setMethod.invoke(null, BaseComponent.this, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private Double _get() {
            try {
                return (Double) getMethod.invoke(null, BaseComponent.this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private static final Double UNDEFINED = Double.MIN_VALUE;

        @Override
        public void set(double value) {
            super.set(value);
            boolean ignore = Math.abs(value - UNDEFINED) < 0.00001d;
            _set(ignore ? null : value);
        }

        @Override
        public double get() {
            return super.get();
        }
    }

    protected DoubleProperty top = new AnchorProperty(this, "top", AnchorProperty.UNDEFINED);

    public DoubleProperty topProperty() {
        return top;
    }

    public Double getTop() {
        return top.get();
    }

    public void setTop(Double top) {
        this.top.set(top);
    }

    protected DoubleProperty right = new AnchorProperty(this, "right", AnchorProperty.UNDEFINED);

    public DoubleProperty rightProperty() {
        return right;
    }

    public Double getRight() {
        return right.get();
    }

    public void setRight(Double right) {
        this.right.set(right);
    }

    protected DoubleProperty bottom = new AnchorProperty(this, "bottom", AnchorProperty.UNDEFINED);

    public DoubleProperty bottomProperty() {
        return bottom;
    }

    public Double getBottom() {
        return bottom.get();
    }

    public void setBottom(Double bottom) {
        this.bottom.set(bottom);
    }

    protected DoubleProperty left = new AnchorProperty(this, "left", AnchorProperty.UNDEFINED);

    public DoubleProperty leftProperty() {
        return left;
    }

    public Double getLeft() {
        return left.get();
    }

    public void setLeft(Double left) {
        this.left.set(left);
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

    public BooleanProperty selected = new SimpleBooleanProperty(this, "selected", false);

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public Boolean getSelected() {
        return selected.get();
    }

    public void setSelected(Boolean selected) {
        this.selected.set(selected);
    }


}
