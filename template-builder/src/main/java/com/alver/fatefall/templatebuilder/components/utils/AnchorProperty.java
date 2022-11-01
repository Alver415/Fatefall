package com.alver.fatefall.templatebuilder.components.utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class AnchorProperty extends SimpleDoubleProperty {
    public enum Anchor {
        TOP, RIGHT, BOTTOM, LEFT;
    }

    protected final Anchor anchor;

    public AnchorProperty(Object bean, Anchor anchor, Double initialValue) {
        this(bean, anchor, initialValue == null ? Region.USE_COMPUTED_SIZE : initialValue);
    }

    public AnchorProperty(Object bean, Anchor anchor, double initialValue) {
        super(bean, anchor.name(), initialValue);
        this.anchor = anchor;
    }


    @Override
    public void set(double value) {
        super.set(value);
        Double anchorValue = value == Region.USE_COMPUTED_SIZE ? null : value;
        switch (anchor) {
            case TOP -> AnchorPane.setTopAnchor((Node) getBean(), anchorValue);
            case RIGHT -> AnchorPane.setRightAnchor((Node) getBean(), anchorValue);
            case BOTTOM -> AnchorPane.setBottomAnchor((Node) getBean(), anchorValue);
            case LEFT -> AnchorPane.setLeftAnchor((Node) getBean(), anchorValue);
        }
    }

    @Override
    public double get() {
        if (true) return super.get();
        return switch (anchor) {
            case TOP -> AnchorPane.getTopAnchor((Node) getBean());
            case RIGHT -> AnchorPane.getRightAnchor((Node) getBean());
            case BOTTOM -> AnchorPane.getBottomAnchor((Node) getBean());
            case LEFT -> AnchorPane.getLeftAnchor((Node) getBean());
        };
    }
}

