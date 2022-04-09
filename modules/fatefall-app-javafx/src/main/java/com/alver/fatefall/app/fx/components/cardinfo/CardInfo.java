package com.alver.fatefall.app.fx.components.cardinfo;

import com.alver.fatefall.app.FxComponent;
import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.cardview.CardView;
import com.alver.fatefall.app.services.DialogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;

public class CardInfo extends BorderPane implements FxComponent {

    @Autowired
    protected FatefallApi fatefallApi;
    @Autowired
    protected DialogService dialogService;

    @FXML
    protected CardView cardView;
    @FXML
    protected TextArea textArea;


    protected final static Border BLACK = new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    protected final static Border ORANGE = new Border(new BorderStroke(Color.ORANGE,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    protected final static Border RED = new Border(new BorderStroke(Color.RED,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    protected final static Border GREEN = new Border(new BorderStroke(Color.GREEN,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

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
        cardProperty.bindBidirectional(cardView.cardProperty());
        cardProperty.addListener((observable, oldValue, newValue) -> refresh());

        textArea.setOnKeyPressed(e -> {
            //Hotkey for submit is Ctrl+Enter
            if (e.isControlDown() && e.getCode() == KeyCode.ENTER) {
                submit();
            }
        });
    }

    private void submit() {
        textArea.setBorder(ORANGE);
        textArea.setDisable(true);
        runAsync(() -> {
            try {
                Card cardWithEdits = getCardWithEdits();
                Card rendered = fatefallApi.getCardApi().generateImage(cardWithEdits);
                setCard(rendered);
                textArea.setBorder(GREEN);
                runAsync(() -> textArea.setBorder(BLACK), 500);
            } catch (Exception e) {
                textArea.setBorder(RED);
                throw new RuntimeException(e);
            } finally {
                textArea.setDisable(false);
            }
        });
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
