package com.other.fatefall.mse.plugin.actions;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardAttribute;
import com.alver.fatefall.api.models.CardCollection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.other.fatefall.mse.SetManager;
import com.other.fatefall.mse.plugin.MSEPlugin;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

@Component
@Extension
public class ImportMSESetAction implements ActionEventHandler {

    @Autowired
    protected MSEPlugin plugin;

    @Override
    public String getName() {
        return "Import from Magic Set Editor";
    }

    @Override
    public void handle(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(Path.of("mse_sets").toFile());
        File file = fileChooser.showOpenDialog(null);
        if (!file.getName().endsWith("mse-set")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Invalid file: " + file);
            alert.show();
            return;
        }

        try {
            SetManager setManager = SetManager.importMseSet(file.getName(), file.toPath());
            ObjectNode set = setManager.getSet();
            CardCollection cardCollection = new CardCollection();

            JsonNode setInfo = set.get("set_info");
            String setName = setInfo.get("title").asText();
            cardCollection.setName(setName);

            ArrayNode cards = (ArrayNode) set.get("card");
            cards.elements().forEachRemaining(c -> {
                Card card = new Card();
                try {
                    card.setData(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(c));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                String cardName = c.get("name").asText();
                card.setName(cardName);
                for (Iterator<String> it = c.fieldNames(); it.hasNext(); ) {
                    String field = it.next();
                    CardAttribute<String> attribute = new CardAttribute<>();
                    attribute.setName(field);
                    attribute.setType(String.class);
                    attribute.getProperty().setValue(c.get(field).asText());
                    card.getAttributeList().add(attribute);
                }

                String cardImageFileName = cardName
                        .replace(" ", "_")
                        .replace(",", "");
                String name_2 = c.findPath("name_2").asText();
                if (name_2.isEmpty()) {
                    card.setFrontUrl("file:" + setManager.getImagesPath().resolve(cardImageFileName + ".png"));
                    card.setBackUrl(SetManager.DEFAULT_CARD_BACK_FACE);
                } else {
                    card.setFrontUrl("file:" + setManager.getImagesPath().resolve(cardImageFileName + ".card_front.png"));
                    card.setBackUrl("file:" + setManager.getImagesPath().resolve(cardImageFileName + ".card_back.png"));
                }
                cardCollection.getCardList().add(card);
            });
            plugin.createCardCollection(cardCollection);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to import file: " + file + "\n" + e.getMessage());
            alert.show();
            throw new RuntimeException(e);
        }
    }

}
