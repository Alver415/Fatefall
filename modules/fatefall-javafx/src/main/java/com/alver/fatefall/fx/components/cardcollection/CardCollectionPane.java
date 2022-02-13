package com.alver.fatefall.fx.components.cardcollection;

import com.alver.fatefall.FatefallApplication;
import com.alver.fatefall.FxApplicationErrorHandler;
import com.alver.fatefall.database.CardCollection;
import com.alver.fatefall.database.DatabaseManager;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.scryfall.api.ScryfallClient;
import com.scryfall.api.models.Card;
import com.scryfall.api.models.CardList;
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

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alver.fatefall.ApplicationUtil.runAsync;
import static com.alver.fatefall.ApplicationUtil.runFx;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class CardCollectionPane extends BorderPane {
    public static final URL FXML = CardCollectionPane.class.getResource("card-collection.fxml");

    /**
     * Spring Dependency Injection
     */
    @Autowired
    protected ScryfallClient client;
    @Autowired
    protected DatabaseManager databaseManager;
    @Autowired
    protected FxApplicationErrorHandler errorHandler;

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

    protected CardCollection collection;
    protected Map<Card, CardView> map = new HashMap<>();

    @FXML
    protected GridPane gridPane;

    public CardCollectionPane() {
        FatefallApplication.load(FXML, this);
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

    public void setCardCollection(CardCollection cardCollection) {
        collection = cardCollection;
        redraw(cardCollection.getCards());
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

    private void search(String query) {
        if (query.isBlank()) {
            redraw(collection.getCards());
        } else {
            runAsync(() -> {
                CardList cards = client.cards().search(query);
                if (cards == null || cards.data().isEmpty()) {
                    errorHandler.alert(INFORMATION,
                            "No Results",
                            "There were no results for the search query.",
                            query);
                    return;
                }
                Set<String> names = collection.getCards().stream().map(Card::name).collect(Collectors.toSet());
                List<Card> cardList = cards.data().stream().filter(c -> names.contains(c.name())).toList();

                runFx(() -> redraw(cardList));
            });
        }
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