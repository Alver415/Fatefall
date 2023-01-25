package com.other.fatefall.mse.plugin.actions;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.CardDeserializer;
import com.alver.fatefall.app.services.DialogManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.other.fatefall.mse.SetManager;
import com.other.fatefall.mse.plugin.MSEPlugin;
import javafx.event.ActionEvent;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Extension
public class ImportMSESetAction implements ActionEventHandler {

	@Autowired
	protected MSEPlugin plugin;
	@Autowired
	protected CardDeserializer cardDeserializer;
	@Autowired
	@Lazy
	protected DialogManager dialogManager;

	@Override
	public String getName() {
		return "Import from Magic Set Editor";
	}

	@Override
	public void handle(ActionEvent event) {
		Path directory = Path.of("mse_sets");
		ensureDirectoryExists(directory);
		dialogManager.showFileSelector(file -> {
			if (!file.getName().endsWith("mse-set")) {
				dialogManager.showAlert("Invalid file: " + file);
			} else {
				importMse(file);
			}
		});
	}

	private void importMse(File file) {
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
					card.addChild(new Attribute("_front_", frontUrl));
					card.addChild(new Attribute("_back_", SetManager.DEFAULT_CARD_BACK_FACE));
				} else {
					String frontUrl = "file:" + setManager.getImagesPath().resolve(cardImageFileName + ".card_front.png");
					String backUrl = "file:" + setManager.getImagesPath().resolve(cardImageFileName + ".card_back.png");
					card.addChild(new Attribute("_front_", frontUrl));
					card.addChild(new Attribute("_back_", backUrl));
				}
				workspace.getCards().add(card);
			});
			plugin.createWorkspace(workspace);
		} catch (IOException e) {
			dialogManager.showAlert("Failed to import file: " + file + "\n" + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	private static void ensureDirectoryExists(Path directory) {
		try {
			Files.createDirectories(directory);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
