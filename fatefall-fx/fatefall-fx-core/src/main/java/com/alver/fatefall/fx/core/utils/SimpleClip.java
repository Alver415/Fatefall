package com.alver.fatefall.fx.core.utils;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Rectangle;

public class SimpleClip extends Rectangle {

    public SimpleClip(DoubleBinding width, DoubleBinding height, DoubleBinding arcWidth, DoubleBinding arcHeight) {
        this.widthProperty().bind(width);
        this.heightProperty().bind(height);
        this.arcWidthProperty().bind(arcWidth);
        this.arcHeightProperty().bind(arcHeight);
    }
}
