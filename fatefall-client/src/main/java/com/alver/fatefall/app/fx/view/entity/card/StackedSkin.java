package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Prototype
public class StackedSkin extends CardViewSkin {

    @Autowired
    protected StackedSkin(CardViewImpl control, FatefallProperties properties) {
        super(control, properties);

        StackPane wrapper = new StackPane(backWrapper, frontWrapper);
        wrapper.setMaxSize(0, 0);
        getChildren().setAll(wrapper);

        StackPane.setMargin(frontWrapper, new Insets(20, 20, 0, 0));
        StackPane.setMargin(backWrapper, new Insets(0, 0, 20, 20));
        
        List.of(frontWrapper, backWrapper).forEach(face -> face.setOnMouseClicked(e -> {
            if (face.getParent().getChildrenUnmodifiable().indexOf(face) == 0) {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(true);
                timeline.setCycleCount(2);
                KeyFrame open = new KeyFrame(Duration.seconds(0),
                        new KeyValue(frontWrapper.translateXProperty(), 0),
                        new KeyValue(backWrapper.translateXProperty(), 0),
                        new KeyValue(frontWrapper.translateYProperty(), 0),
                        new KeyValue(backWrapper.translateYProperty(), 0));
                KeyFrame close = new KeyFrame(Duration.seconds(0.1), a -> face.toFront(),
                        new KeyValue(frontWrapper.translateXProperty(), -50),
                        new KeyValue(backWrapper.translateXProperty(), 50),
                        new KeyValue(frontWrapper.translateYProperty(), 50),
                        new KeyValue(backWrapper.translateYProperty(), -50));
                timeline.getKeyFrames().setAll(open, close);
                timeline.play();
            }
        }));
    }
}
