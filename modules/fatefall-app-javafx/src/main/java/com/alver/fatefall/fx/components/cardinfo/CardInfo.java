package com.alver.fatefall.fx.components.cardinfo;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.alver415.javafx.scene.control.input.InputTextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class CardInfo extends VBox implements FxComponent {

    @Autowired
    protected FatefallApiClient fatefallApiClient;

    @FXML
    protected CardView cardView;
    @FXML
    protected VBox dataFields;
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
        ObjectNode data = card.getData();
        ObservableList<Node> nodes = dataFields.getChildren();
        for (Node node : nodes){
            InputTextField field = (InputTextField) node;
            String label = field.getLabelText();
            String value = field.getValue();
            data.put(label, value);
        }
        return card;
    }

    private void refresh() {
        dataFields.getChildren().clear();
        if (cardProperty.get() == null){
            return;
        }
        ObjectNode data = cardProperty.get().getData();
        for (Iterator<String> it = data.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            String value = data.get(field).textValue();
            InputTextField input = new InputTextField();
            input.setLabelText(field);
            input.setValue(value);
            dataFields.getChildren().add(input);
        }

    }

}
