package com.alver.fatefall.app.fx.components.cardinfo;

import com.alver.fatefall.JsonUtil;
import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.FxComponent;
import com.alver.fatefall.app.fx.components.cardview.CardView;
import com.alver.fatefall.app.services.DialogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import component.CardEditor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import plugin.ScriptPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CardInfo extends BorderPane implements FxComponent {

    @Autowired
    protected FatefallApi fatefallApi;
    @Autowired
    protected DialogService dialogService;

    @FXML
    protected CardView cardView;
    @FXML
    protected CardEditor cardEditor;
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
        cardEditor.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.S) && event.isControlDown()){
                WritableImage snapshot = cardEditor.snapshot(new SnapshotParameters(), null);
                getCard().setFrontFaceImage(snapshot);
            }
        });
        cardView.setVisible(false);
        cardProperty.bindBidirectional(cardView.cardProperty());
        cardProperty.addListener((observable, oldValue, newValue) -> refresh());

        textArea.setOnKeyPressed(e -> {
            //Hotkey for submit is Ctrl+Enter
            if (e.isControlDown() && e.getCode() == KeyCode.ENTER) {
                try {
                    Card card = script();
                    submit(card);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private Card script() throws IOException {
        JsonNode master = JsonUtil.fromJson(new File("scripts/master.json"));
        ArrayNode array = (ArrayNode) master.get("scripts");
        Map<String, JsonNode> map = new HashMap<>();
        array.forEach(e -> map.put(e.get("title").asText(), e));
        Set<String> choices = map.keySet();
        Optional<String> script_selection =
                dialogService.choiceInput(
                        "Script Selection",
                        "What script would you like to execute?",
                        choices);
        String selectedTitle = script_selection.get();
        JsonNode selected = map.get(selectedTitle);
        Card card = getCardWithEdits();
        JsonNode data = card.getData();
        String file = "scripts/" + selected.get("file").asText();
        String converted = ScriptPlugin.convert(data, file);
        card.setJson(converted);
        return card;
    }
    private void submit(Card card) {
        textArea.setBorder(ORANGE);
        textArea.setDisable(true);
        runAsync(() -> {
            try {
                Card rendered = fatefallApi.getCardApi().generateImage(card);
                setCard(rendered);
                textArea.setText(rendered.getJsonPretty());
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
