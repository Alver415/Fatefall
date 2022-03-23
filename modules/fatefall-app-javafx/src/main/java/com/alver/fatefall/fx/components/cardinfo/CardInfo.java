package com.alver.fatefall.fx.components.cardinfo;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.alver.fatefall.services.DialogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;

public class CardInfo extends VBox implements FxComponent {

    @Autowired
    protected FatefallApiClient fatefallApiClient;
    @Autowired
    protected DialogService dialogService;

    @FXML
    protected CardView cardView;
    @FXML
    protected TextArea textArea;
    @FXML
    protected Button renderButton;

    /**
     * === Selected Property ==
     */
    protected ObjectProperty<Card> cardProperty = new SimpleObjectProperty<>();
    public final void setCard(Card value) {
        cardProperty.set(value);
    }
    public final Card getCard() {
        return cardProperty.get();
    }
    public final ObjectProperty<Card> cardProperty() {
        return cardProperty;
    }

    public CardInfo() {
        initFxml();
    }

    @FXML
    public void initialize() {
        renderButton.setOnMouseClicked(e -> render());
        cardProperty.bindBidirectional(cardView.cardProperty());
        cardProperty.addListener((observable, oldValue, newValue) -> refresh());
    }
    private void render() {
        Card cardWithEdits = getCardWithEdits();
        Card rendered = fatefallApiClient.getCardApi().generateImage(cardWithEdits);
        setCard(rendered);
    }

    private Card getCardWithEdits() {
        Card card = getCard();
        try {
            card.setJson(textArea.getText());
        } catch (JsonProcessingException e) {
            dialogService.error("Invalid json.");
        }
        return card;
    }

    private void refresh() {
        if (cardProperty.get() == null) {
            textArea.clear();
            textArea.setEditable(false);
        } else {
            textArea.setText(cardProperty.get().getJsonPretty());
            textArea.setEditable(true);
        }
    }

}
