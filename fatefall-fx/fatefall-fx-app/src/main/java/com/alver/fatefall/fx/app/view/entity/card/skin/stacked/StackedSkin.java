package com.alver.fatefall.fx.app.view.entity.card.skin.stacked;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.app.view.entity.card.skin.CardViewSkinBase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.List;

public class StackedSkin extends CardViewSkinBase {

    protected final StackPane wrapper;

    public StackedSkin(CardView control, FatefallProperties properties) {
        super(control);

        wrapper = new StackPane(back, front);
        getChildren().setAll(wrapper);

        StackPane.setMargin(front, new Insets(20, 20, 0, 0));
        StackPane.setMargin(back, new Insets(0, 0, 20, 20));

        List.of(front, back).forEach(face -> face.setOnMouseClicked(_ -> {
            if (wrapper.getChildren().indexOf(face) == 0) {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(true);
                timeline.setCycleCount(2);
                KeyFrame open = new KeyFrame(Duration.seconds(0),
                        new KeyValue(front.translateXProperty(), 0),
                        new KeyValue(back.translateXProperty(), 0),
                        new KeyValue(front.translateYProperty(), 0),
                        new KeyValue(back.translateYProperty(), 0));
                KeyFrame close = new KeyFrame(Duration.seconds(0.1), _ -> face.toFront(),
                        new KeyValue(front.translateXProperty(), -50),
                        new KeyValue(back.translateXProperty(), 50),
                        new KeyValue(front.translateYProperty(), 50),
                        new KeyValue(back.translateYProperty(), -50));
                timeline.getKeyFrames().setAll(open, close);
                timeline.play();
            }
        }));
    }
}
