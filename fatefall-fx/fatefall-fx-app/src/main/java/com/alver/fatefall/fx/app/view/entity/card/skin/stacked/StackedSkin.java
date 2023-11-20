package com.alver.fatefall.fx.app.view.entity.card.skin.stacked;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.app.view.entity.card.skin.AbstractCardViewSkin;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.List;

public class StackedSkin extends AbstractCardViewSkin {

    public StackedSkin(CardView control, FatefallProperties properties) {
        super(control);

        StackPane wrapper = new StackPane(back, front);
        wrapper.scaleXProperty().bind(properties.getCardViewScale());
        wrapper.scaleYProperty().bind(properties.getCardViewScale());
        wrapper.setMaxSize(0, 0);
        getChildren().setAll(new Group(wrapper));

        StackPane.setMargin(front, new Insets(20, 20, 0, 0));
        StackPane.setMargin(back, new Insets(0, 0, 20, 20));

        List.of(front, back).forEach(face -> face.setOnMouseClicked(e -> {
            if (face.getParent().getChildrenUnmodifiable().indexOf(face) == 0) {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(true);
                timeline.setCycleCount(2);
                KeyFrame open = new KeyFrame(Duration.seconds(0),
                        new KeyValue(front.translateXProperty(), 0),
                        new KeyValue(back.translateXProperty(), 0),
                        new KeyValue(front.translateYProperty(), 0),
                        new KeyValue(back.translateYProperty(), 0));
                KeyFrame close = new KeyFrame(Duration.seconds(0.1), a -> face.toFront(),
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
