package com.alver.fatefall.fx.components.cardinfo;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.base.Card;
import com.alver.fatefall.api.base.implementation.ImmutableCard;
import com.alver.fatefall.fx.components.cardview.CardView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mse.MseCliProcess;
import org.alver415.javafx.scene.control.input.InputTextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CardInfo extends VBox implements FxComponent {

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
        generateButton.setOnMouseClicked(e -> generateImage());
        cardProperty.bindBidirectional(cardView.cardProperty());
        cardProperty.addListener((observable, oldValue, newValue) -> refresh());
    }

    protected void save(MouseEvent event) {
        Card cardWithEdits = getCardWithEdits();
//        setCard(new CardRepository().merge(cardWithEdits));
    }

    private Card getCardWithEdits() {
        return ImmutableCard.copyOf(cardProperty.get())
                .withName(name.getValue())
                .withManaCost(manaCost.getValue())
                .withTypeLine(typeLine.getValue())
                .withPower(power.getValue())
                .withToughness(toughness.getValue());
    }

    private void refresh() {
        Card card = cardProperty.get();
        name.setValue(card == null ? null : card.getName());
        manaCost.setValue(card == null ? null : card.getManaCost());
        typeLine.setValue(card == null ? null : card.getTypeLine());
        power.setValue(card == null ? null : card.getPower());
        toughness.setValue(card == null ? null : card.getToughness());

    }

    public void generateImage() {
        runAsync(() -> {
            Card card = getCardWithEdits();
            String fileName = card.getName()
                    .replace(" ", "_").toLowerCase() + ".png";
            try (MseCliProcess mse = new MseCliProcess()) {
                mse.load(Path.of("mse_sets/empty.mse-set"));
                Map<String, String> fieldMap = new HashMap<>();
                fieldMap.put("name", card.getName());
                fieldMap.put("type", card.getTypeLine());
                fieldMap.put("rule_text", card.getOracleText());
                fieldMap.put("power", card.getPower());
                fieldMap.put("toughness", card.getToughness());

                mse.command("source := set.cards[0]");
                mse.new_card("my_card", fieldMap);
                mse.write_image_file("set.cards[0]", fileName);
                mse.quit();

                Path path = Path.of(fileName);
                Image image = new Image("file:" + path);
                runFx(() -> this.cardView.frontFaceProperty().set(image));
                Files.delete(path);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
