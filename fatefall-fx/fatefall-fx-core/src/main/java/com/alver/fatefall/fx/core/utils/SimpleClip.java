package com.alver.fatefall.fx.core.utils;

import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Rectangle;

public class SimpleClip extends Rectangle {

    public SimpleClip(
            ObservableValue<Number> width,
            ObservableValue<Number> height,
            ObservableValue<Number> arcWidth,
            ObservableValue<Number> arcHeight) {
        this.widthProperty().bind(width);
        this.heightProperty().bind(height);
        this.arcWidthProperty().bind(arcWidth);
        this.arcHeightProperty().bind(arcHeight);
    }
}
