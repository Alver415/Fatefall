package com.other.fatefall.mse.plugin.actions;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.api.models.attributes.AttributeFactory;
import com.alver.fatefall.api.models.attributes.StringAttribute;
import com.alver.fatefall.app.CardDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
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

@Component
@Extension
public class ImportMSESetAction implements ActionEventHandler {

    @Autowired
    protected MSEPlugin plugin;
    @Autowired
    protected AttributeFactory attributeFactory;
    @Autowired
    protected CardDeserializer cardDeserializer;

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
            Workspace workspace = new Workspace();

            JsonNode setInfo = set.get("set_info");
            String setName = setInfo.get("title").asText();
            workspace.setName(setName);

            ArrayNode cards = (ArrayNode) set.get("card");
            cards.elements().forEachRemaining(json -> {
                Card card = cardDeserializer.buildCard(json);

                String cardName = json.get("name").asText();
                card.setName(cardName);

                String cardImageFileName = cardName
                        .replace(" ", "_")
                        .replace(",", "");
                String name_2 = json.findPath("name_2").asText();
                if (name_2.isEmpty()) {
                    String frontUrl = "file:" + setManager.getImagesPath().resolve(cardImageFileName + ".png");
                    card.addAttribute(attributeFactory.createAttribute("_front_", StringAttribute.class, frontUrl));
                    card.addAttribute(attributeFactory.createAttribute("_back_", StringAttribute.class, SetManager.DEFAULT_CARD_BACK_FACE));
                } else {
                    String frontUrl = "file:" + setManager.getImagesPath().resolve(cardImageFileName + ".card_front.png");
                    String backUrl = "file:" + setManager.getImagesPath().resolve(cardImageFileName + ".card_back.png");
                    card.addAttribute(attributeFactory.createAttribute("_front_", StringAttribute.class, frontUrl));
                    card.addAttribute(attributeFactory.createAttribute("_back_", StringAttribute.class, backUrl));
                }
                workspace.getCards().add(card);
            });
            plugin.createWorkspace(workspace);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to import file: " + file + "\n" + e.getMessage());
            alert.show();
            throw new RuntimeException(e);
        }
    }

}
