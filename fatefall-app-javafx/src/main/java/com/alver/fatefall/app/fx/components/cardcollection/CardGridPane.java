package com.alver.fatefall.app.fx.components.cardcollection;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.FxComponent;
import com.alver.fatefall.app.fx.components.cardinfo.CardInfo;
import com.alver.fatefall.app.fx.components.cardview.CardView;
import com.alver.fatefall.app.services.DialogService;
import com.alver.scryfall.api.ScryfallApiClient;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CardGridPane extends BorderPane implements FxComponent {

    protected static final int GRID_DIMENSION_ROW = 4;
    protected static final int GRID_DIMENSION_COL = 4;

    /**
     * Spring Dependency Injection
     */
    @Autowired
    protected ScryfallApiClient scryfallClient;
    @Autowired
    protected DialogService errorHandler;

    @FXML
    protected TextField searchInput;
    @FXML
    protected CardInfo cardInfo;

    /**
     * === Selected Property ==
     */
    protected ObjectProperty<CardView> selectedCardViewProperty = new SimpleObjectProperty<>();
    public final void setSelectedCardView(CardView value) {
        selectedCardViewProperty.set(value);
    }
    public final CardView getSelectedCardView() {
        return selectedCardViewProperty.get();
    }
    public final ObjectProperty<CardView> selectedCardViewProperty() {
        return selectedCardViewProperty;
    }

    protected Map<Long, CardView> map = new HashMap<>();

    @FXML
    protected FlowPane flowPane;

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
        selectedCardViewProperty.addListener((observable, oldValue, newValue) -> {
            SelectedCardViewAnimator.animateDeselected(oldValue);
            SelectedCardViewAnimator.animateSelected(newValue);
        });
    }

    protected void redraw(List<Card> cards) {
        flowPane.getChildren().clear();
        for (Card card : cards) {
            CardView cardView = new CardView();
            cardView.setCard(card);
            cardView.setOnMouseClicked((event) -> handleCardViewSelection(cardView, event));
            map.put(card.getPk(), cardView);
            flowPane.getChildren().add(cardView);
        }
    }

    private void handleCardViewSelection(CardView cardView, MouseEvent event) {
        if (event.getButton() != MouseButton.PRIMARY) {
            return;
        }
        // If there is a card view already selected, unbind it from the card info pane.
        CardView previouslySelected = getSelectedCardView();
        if (previouslySelected != null) {
            cardInfo.cardProperty().unbindBidirectional(previouslySelected.cardProperty());
        }
        // Set the newly selected card view and bind the card info pane's card property to it.
        setSelectedCardView(cardView);
        cardInfo.cardProperty().bindBidirectional(getSelectedCardView().cardProperty());
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