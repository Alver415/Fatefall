package com.alver.fatefall.fx.components.cardcollection;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.alver.fatefall.api.models.scryfall.CardList;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScryfallSearchPane extends CardGridPane {

    public ScryfallSearchPane() {
        //Override fxml loading to use CardCollectionPane's fxml.
        super(CardGridPane.class);
    }

    public void search(String query) {
        runAsync(() -> {
            List<Card> cards = scryfallClient.getCardApi().search(query);
            if (cards.isEmpty()) {
                throw new RuntimeException("No results");
            }
            runFx(() -> redraw(cards));
        });
    }

    private static class SelectedCardViewAnimator {

        private static final Duration DURATION = Duration.seconds(0.2);
        private static final double SCALE = 1.05;
        private static final double SHADOW = 10;

        public static void animateSelected(CardView cardView) {
            if (cardView == null) {
                return;
            }
            createTimeline(cardView).playFromStart();
        }

        public static void animateDeselected(CardView cardView) {
            if (cardView == null) {
                return;
            }
            Timeline timeline = createTimeline(cardView);
            timeline.setRate(-timeline.getRate());
            timeline.playFrom(timeline.getTotalDuration());
        }

        private static Timeline createTimeline(CardView cardView) {
            DropShadow shadow = new DropShadow(0, Color.BLACK);
            cardView.setEffect(shadow);

            Timeline selectTimeline = new Timeline();
            selectTimeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(shadow.radiusProperty(), 0),
                            new KeyValue(cardView.viewOrderProperty(), 1),
                            new KeyValue(cardView.scaleXProperty(), 1),
                            new KeyValue(cardView.scaleYProperty(), 1)),
                    new KeyFrame(DURATION,
                            new KeyValue(shadow.radiusProperty(), SHADOW),
                            new KeyValue(cardView.viewOrderProperty(), -1),
                            new KeyValue(cardView.scaleXProperty(), SCALE),
                            new KeyValue(cardView.scaleYProperty(), SCALE))
            );
            return selectTimeline;
        }
    }

}