package com.alver.fatefall;

import com.alver.fatefall.fx.components.CardInfo;
import com.alver.fatefall.fx.components.CardView;
import com.scryfall.api.ScryfallClient;
import com.scryfall.api.models.Card;
import com.scryfall.api.models.CardList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CardSearchController {

    protected final ScryfallClient client;
    protected final ImageRepository imageRepository;

    public CardSearchController(ScryfallClient client, ImageRepository imageRepository) {
        this.client = client;
        this.imageRepository = imageRepository;
    }

    @FXML
    protected TextField searchInput;

    @FXML
    protected GridPane gridPane;

    @FXML
    protected CardInfo cardInfo;

    protected CardView selected;


    @FXML
    public void initialize() {
        cardInfo.setImageRepository(imageRepository);
        this.searchInput.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                search(searchInput.getText());
            }
        });
    }

    private void search(String query) {
        runAsync(() -> {
            CardList cards = client.cards().search(query);
            runFX(() -> {
                this.gridPane.getChildren().clear();
                for (int i = 0; i < cards.data().size(); i++) {
                    int col = i % gridPane.getColumnCount();
                    int row = i / gridPane.getColumnCount();

                    Card card = cards.data().get(i);
                    CardView cardView = new CardView(card);
                    cardView.setImageRepository(imageRepository);
                    gridPane.add(cardView, col, row);
                    cardView.setOnMouseClicked((event) -> {
                        setSelected(cardView);
                    });
                }
            });
        });
    }

    private void setSelected(CardView cardView) {
        if (selected != null) {
            SelectedCardViewAnimator.animateDeselected(selected);
        }

        if (selected == cardView) {
            selected = null;
            cardInfo.setCard(null);
        } else {
            selected = cardView;
            cardInfo.setCard(selected.getCard());
            SelectedCardViewAnimator.animateSelected(cardView);
        }
    }

    private static class SelectedCardViewAnimator {

        private static final double SCALE = 1.1;
        private static final double SHADOW = 100;

        public static void animateSelected(CardView cardView){
            cardView.toFront();
            createTimeline(cardView).playFromStart();
        }
        public static void animateDeselected(CardView cardView){
            Timeline timeline = createTimeline(cardView);
            timeline.setRate(-timeline.getRate());
            timeline.playFrom(timeline.getTotalDuration());
        }
        private static Timeline createTimeline(CardView cardView) {
            DropShadow shadow = new DropShadow(0, Color.BLACK);
            cardView.setEffect(shadow);

            Timeline selectTimeline = new Timeline();
            selectTimeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(0),
                            new KeyValue(shadow.radiusProperty(), 0),
                            new KeyValue(cardView.scaleXProperty(), 1),
                            new KeyValue(cardView.scaleYProperty(), 1)),
                    new KeyFrame(Duration.seconds(0.2),
                            new KeyValue(shadow.radiusProperty(), SHADOW),
                            new KeyValue(cardView.scaleXProperty(), SCALE),
                            new KeyValue(cardView.scaleYProperty(), SCALE))
            );
            return selectTimeline;
        }
    }

    private void runAsync(Runnable runnable) {
        new Thread(runnable).start();
    }

    private void runFX(Runnable runnable) {
        Platform.runLater(runnable);
    }
}