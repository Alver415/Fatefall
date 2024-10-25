package com.other.fatefall.mse.plugin.actions;

import com.alver.fatefall.fx.app.view.entity.card.template.ImageTemplate;
import com.alver.fatefall.fx.core.interfaces.AppEvent;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.other.fatefall.mse.SetManager;
import com.other.fatefall.mse.plugin.MSEPlugin;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Extension
public class ImportMSESetAction implements AppEvent, ExtensionPoint {

	@Autowired
	protected MSEPlugin plugin;
	@Autowired
	protected ObjectMapper objectMapper;

	@Override
	public String getTitle() {
		return "Import from Magic Set Editor";
	}

	private static final ImageTemplate DEFAULT_BACK_TEMPLATE =
			new ImageTemplate(new Image(SetManager.DEFAULT_CARD_BACK_FACE));

	@Override
	public void handle(ActionEvent event) {
		Path directory = Path.of("mse_sets");
		ensureDirectoryExists(directory);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Magic Set Editor Files", "*.mse-set");
		fileChooser.getExtensionFilters().add(filter);
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			importMse(selectedFile);
		}
	}

	private void importMse(File file) {
		try {
			SetManager setManager = SetManager.importMseSet(file.getName(), file.toPath());
			ObjectNode set = setManager.getSet();
			WorkspaceFX workspace = new WorkspaceFX();

			JsonNode setInfo = set.get("set_info");
			String setName = setInfo.get("title").asText();
			workspace.setName(setName);

			ArrayNode cards = (ArrayNode) set.get("card");
			cards.elements().forEachRemaining(json -> {
				CardFX card = new CardFX();
				TreeProperty<Object> deserialized = jsonToData(json);
				card.dataProperty().setValue(deserialized.getValue());
				card.dataProperty().getChildrenMap().putAll(deserialized.getChildrenMap());

				String cardName = json.get("name").asText();
				card.setName(cardName);

				String cardImageFileName = cardName
						.replace(" ", "_")
						.replace(",", "");
				String name_2 = json.findPath("name_2").asText();
				if (name_2.isEmpty()) {
					String frontUrl = "file:" + setManager.getImagesPath().resolve(cardImageFileName + ".png");
					card.getFront().setTemplate(new ImageTemplate(new Image(frontUrl)));
					card.getBack().setTemplate(DEFAULT_BACK_TEMPLATE);
				} else {
					String frontUrl = "file:" + setManager.getImagesPath().resolve(
							cardImageFileName + ".card_front.png");
					String backUrl = "file:" + setManager.getImagesPath().resolve(cardImageFileName + ".card_back" +
							".png");
					card.getFront().setTemplate(new ImageTemplate(new Image(frontUrl)));
					card.getBack().setTemplate(new ImageTemplate(new Image(backUrl)));
				}
				workspace.addCards(card);
			});
			plugin.createWorkspace(workspace);
		} catch (IOException e) {
			new Alert(Alert.AlertType.ERROR, "Failed to import file: " + file + "\n" + e.getMessage()).show();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private TreeProperty<Object> jsonToData(JsonNode json) {
		try {
			return objectMapper.treeToValue(json, TreeProperty.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private CardFX jsonToCard(JsonNode json) {
		try {
			return objectMapper.treeToValue(json, CardFX.class);
		} catch (JsonProcessingException e) {
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
