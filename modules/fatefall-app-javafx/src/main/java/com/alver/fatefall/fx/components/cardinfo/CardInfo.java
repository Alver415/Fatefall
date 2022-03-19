package com.alver.fatefall.fx.components.cardinfo;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.api.models.scryfall.Card;
import com.alver.fatefall.fx.components.cardview.CardView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.alver415.javafx.scene.control.input.InputTextField;
import org.springframework.beans.factory.annotation.Autowired;

public class CardInfo extends VBox implements FxComponent {

    @Autowired
    protected FatefallApiClient fatefallApiClient;

    @FXML
    protected CardView cardView;
    @FXML
    protected VBox cardFields;
    @FXML
    protected InputTextField name;
    @FXML
    protected InputTextField manaCost;
    @FXML
    protected InputTextField typeLine;
    @FXML
    protected InputTextField power;
    @FXML
    protected InputTextField toughness;
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
        generateButton.setOnMouseClicked(e -> setCard(fatefallApiClient.getCardApi().generateImage(getCard())));
        cardProperty.bindBidirectional(cardView.cardProperty());
        cardProperty.addListener((observable, oldValue, newValue) -> refresh());
    }

    protected void save(MouseEvent event) {
        Card cardWithEdits = getCardWithEdits();
        Card saved = fatefallApiClient.getCardApi().save(cardWithEdits);
        setCard(saved);
    }

    private Card getCardWithEdits() {
        return cardProperty.get()
                .withName(name.getValue())
                .withManaCost(manaCost.getValue())
                .withTypeLine(typeLine.getValue())
                .withPower(power.getValue())
                .withToughness(toughness.getValue());
    }

    private void refresh() {
        Card card = cardProperty.get();
        name.setValue(card == null ? null : card.name());
        manaCost.setValue(card == null ? null : card.manaCost());
        typeLine.setValue(card == null ? null : card.typeLine());
        power.setValue(card == null ? null : card.power());
        toughness.setValue(card == null ? null : card.toughness());

    }

}
