package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@FXMLAutoLoad
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StackedFacesCardView extends AbstractCardView<StackedFacesCardView> {

    @FXML
    private void initialize() {
        List.of(frontFace, backFace).forEach(face -> face.setOnMouseClicked(e -> {
            if (face.getParent().getChildrenUnmodifiable().indexOf(face) == 0) {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(true);
                timeline.setCycleCount(2);
                KeyFrame open = new KeyFrame(Duration.seconds(0),
                        new KeyValue(frontFace.translateXProperty(), 0),
                        new KeyValue(backFace.translateXProperty(), 0),
                        new KeyValue(frontFace.translateYProperty(), 0),
                        new KeyValue(backFace.translateYProperty(), 0));
                KeyFrame close = new KeyFrame(Duration.seconds(0.05), a -> face.toFront(),
                        new KeyValue(frontFace.translateXProperty(), -50),
                        new KeyValue(backFace.translateXProperty(), 50),
                        new KeyValue(frontFace.translateYProperty(), 50),
                        new KeyValue(backFace.translateYProperty(), -50));
                timeline.getKeyFrames().setAll(open, close);
                timeline.play();
            }
        }));
    }
}
