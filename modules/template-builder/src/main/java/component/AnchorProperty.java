package component;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Method;
import java.util.Objects;

class AnchorProperty extends SimpleDoubleProperty {

    public static final String TOP_ANCHOR = "pane-top-anchor";
    public static final String LEFT_ANCHOR = "pane-left-anchor";
    public static final String BOTTOM_ANCHOR = "pane-bottom-anchor";
    public static final String RIGHT_ANCHOR = "pane-right-anchor";

    private final Method getMethod;
    private final Method setMethod;

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

    public static void addAnchorChangeListener(Component component) {
        component.getProperties().addListener((MapChangeListener<? super Object, ? super Object>) event -> {
            if (Objects.equals(AnchorProperty.TOP_ANCHOR, event.getKey())) {
                component.setTop((Double) event.getValueAdded());
            } else if (Objects.equals(AnchorProperty.RIGHT_ANCHOR, event.getKey())) {
                component.setRight((Double) event.getValueAdded());
            } else if (Objects.equals(AnchorProperty.BOTTOM_ANCHOR, event.getKey())) {
                component.setBottom((Double) event.getValueAdded());
            } else if (Objects.equals(AnchorProperty.LEFT_ANCHOR, event.getKey())) {
                component.setLeft((Double) event.getValueAdded());
            }
        });
    }

    private void _set(Double value) {
        try {
            setMethod.invoke(null, (Node) getBean(), value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Double _get() {
        try {
            return (Double) getMethod.invoke(null, (Node) getBean());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final Double UNDEFINED = Double.MIN_VALUE;

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

