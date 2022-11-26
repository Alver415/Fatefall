package com.alver.fatefall.app.fx.components.cardinfo;

import com.alver.fatefall.JsonUtil;
import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.FxComponent;
import com.alver.fatefall.app.fx.components.cardview.CardFace;
import com.alver.fatefall.app.fx.components.cardview.CardPane;
import com.alver.fatefall.app.fx.components.cardview.CardView;
import com.alver.fatefall.app.fx.components.cardview.ImageBlock;
import com.alver.fatefall.app.services.DialogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import plugin.ScriptPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.alver.fatefall.app.fx.components.Borders.*;

public class CardInfo extends BorderPane implements FxComponent {

    @Autowired
    protected FatefallApi fatefallApi;
    @Autowired
    protected DialogService dialogService;

    @FXML
    protected CardView cardView;
    @FXML
    protected TextArea textArea;

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
        cardView.cardProperty().bind(cardProperty);
        cardProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                CardPane card = new CardPane();
                ImageBlock frontImage = new ImageBlock();
                frontImage.setImage(new Image(newValue.getFrontFaceUrl()));
                ImageBlock backImage = new ImageBlock();
                backImage.setImage(new Image(newValue.getBackFaceUrl()));
                card.setFront(new CardFace(frontImage));
                card.setBack(new CardFace(backImage));
            }
        });
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
