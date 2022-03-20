package com.alver.fatefall.fx.components.cardinfo;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.alver.fatefall.services.DialogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

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
    protected Button saveButton;
    @FXML
    protected Button generateButton;

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
        saveButton.setOnMouseClicked(this::save);
        generateButton.setOnMouseClicked(e -> setCard(fatefallApiClient.getCardApi().generateImage(getCardWithEdits())));
        cardProperty.bindBidirectional(cardView.cardProperty());
        cardProperty.addListener((observable, oldValue, newValue) -> refresh());
    }

    protected void save(MouseEvent event) {
        Card cardWithEdits = getCardWithEdits();
        Card saved = fatefallApiClient.getCardApi().save(cardWithEdits);
        setCard(saved);
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
        if (cardProperty.get() == null){
            textArea.clear();
            textArea.setEditable(false);
        } else {
            textArea.setText(cardProperty.get().getJsonPretty());
            textArea.setEditable(true);
        }
    }

}
