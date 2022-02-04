package com.alver.fatefall.fx.components;

import com.alver.fatefall.CardRepo;
import com.alver.fatefall.ImageRepository;
import com.scryfall.api.models.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.alver415.javafx.scene.control.input.InputTextField;

public class CardInfo extends VBox implements Controller {

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

    protected Card card;

    protected ImageRepository imageRepository;

    public CardInfo() {
        this(null);
    }

    public CardInfo(Card card) {
        this.card = card;
        fxmlLoad("card-info.fxml");
    }

    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void setCard(Card card) {
        this.card = card;
        runFx(this::refresh);
    }

    protected void save(MouseEvent event) {

        Card cardWithEdits = card
                .withName(name.getValue())
                .withManaCost(manaCost.getValue())
                .withTypeLine(typeLine.getValue())
                .withPower(power.getValue())
                .withToughness(toughness.getValue());

        setCard(new CardRepo().merge(cardWithEdits));

    }

    @FXML
    public void initialize() {
        saveButton.setOnMouseClicked(this::save);
        refresh();
    }

    private void refresh() {
        if (card != null) {
            cardView.setImageRepository(imageRepository);
            name.setValue(card.name());
            manaCost.setValue(card.manaCost());
            typeLine.setValue(card.typeLine());
            power.setValue(card.power());
            toughness.setValue(card.toughness());
        } else {
            cardView.setCard(null);
            name.setValue(null);
            manaCost.setValue(null);
            typeLine.setValue(null);
            power.setValue(null);
            toughness.setValue(null);
        }
    }
}
