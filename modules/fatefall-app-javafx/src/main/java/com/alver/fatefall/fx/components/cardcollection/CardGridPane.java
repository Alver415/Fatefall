package com.alver.fatefall.fx.components.cardcollection;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.alver.fatefall.services.DialogService;
import com.alver.scryfall.api.ScryfallClient;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CardGridPane extends BorderPane implements FxComponent {

    /**
     * Spring Dependency Injection
     */
    @Autowired
    protected ScryfallClient client;
    @Autowired
    protected DialogService errorHandler;

    @FXML
    protected TextField searchInput;

    /**
     * === Selected Property ==
     */
    protected ObjectProperty<Card> selectedCard = new SimpleObjectProperty<>();
    public final void setSelectedCard(Card value) {
        selectedCard.set(value);
    }
    public final Card getSelectedCard() {
        return selectedCard.get();
    }
    public final ObjectProperty<Card> selectedCardProperty() {
        return selectedCard;
    }

    protected Map<Card, CardView> map = new HashMap<>();

    @FXML
    protected GridPane gridPane;

    public CardGridPane() {
        this(CardGridPane.class);
    }
    protected CardGridPane(Class<?> clazz) {
        initFxml(clazz);
    }

    @FXML
    public void initialize() {
        searchInput.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                search(searchInput.getText());
            }
        });
        selectedCard.addListener((observable, oldValue, newValue) -> {
            SelectedCardViewAnimator.animateDeselected(map.get(oldValue));
            SelectedCardViewAnimator.animateSelected(map.get(newValue));
        });
    }

    protected void redraw(List<Card> cards) {
        gridPane.getChildren().clear();
        //TODO: Remove limit and implement pagination.
        for (int i = 0; i < Math.min(cards.size(), 20); i++) {
            int col = i % gridPane.getColumnCount();
            int row = i / gridPane.getColumnCount();
            Card card = cards.get(i);
            CardView cardView = new CardView();
            cardView.setCard(card);
            cardView.setOnMouseClicked((event) -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    setSelectedCard(getSelectedCard() == card ? null : card);
                }
            });
            map.put(card, cardView);
            gridPane.add(cardView, col, row);
        }
    }

    public abstract void search(String query);

    protected static class SelectedCardViewAnimator {

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