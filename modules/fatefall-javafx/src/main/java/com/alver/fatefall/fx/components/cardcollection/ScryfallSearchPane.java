package com.alver.fatefall.fx.components.cardcollection;

import com.alver.fatefall.database.CardCollection;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.scryfall.api.models.Card;
import com.scryfall.api.models.CardList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alver.fatefall.ApplicationUtil.runAsync;
import static com.alver.fatefall.ApplicationUtil.runFx;

public class ScryfallSearchPane extends CardCollectionPane {

    public ScryfallSearchPane() {
        super();
    }

    public void setCardCollection(CardCollection cardCollection) {
        throw new UnsupportedOperationException();
    }

    private void search(String query) {
        runAsync(() -> {
            CardList cards = client.cards().search(query);
            if (cards == null) {
                throw new RuntimeException("No results");
            }
            Set<String> names = collection.getCards().stream().map(Card::name).collect(Collectors.toSet());
            List<Card> cardList = cards.data().stream().filter(c -> {
                return names.contains(c.name());
            }).toList();

            runFx(() -> redraw(cardList));
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