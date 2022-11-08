package com.alver.fatefall.templatebuilder.app;

import com.alver.fatefall.templatebuilder.app.console.PolyglotConsole;
import com.alver.fatefall.templatebuilder.components.block.*;
import com.alver.fatefall.templatebuilder.components.editor.file.FileSelectionField;
import com.alver.fatefall.templatebuilder.components.editor.image.ImageSelectionEditor;
import com.alver.fxmlsaver.FXMLSaver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanProperty;
import org.graalvm.polyglot.Context;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Properties;

public class TemplateBuilder extends Stage {

	@FXML
	public BorderPane borderPane;
	@FXML
	public FileSelectionField templateSelector;
	@FXML
	public FileSelectionField cardSelector;
	@FXML
	protected PropertySheet templateProperties;
	@FXML
	protected PropertySheet cardProperties;
	@FXML
	protected CardEditor editor;

	protected Stage consoleStage;

	@FXML
	public void initialize() {
		templateSelector.getExtensions().add("*.fxml");
		cardSelector.getExtensions().add("*.card");

		borderPane.setBackground(new Background(new BackgroundImage(
				ImageUtil.getTransparencyGrid(16, 16),
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT
		)));

		addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown() && event.getCode().equals(KeyCode.S)) {
				promptSaveCard();
			} else if (event.isControlDown() && event.getCode().equals(KeyCode.L)) {
				promptLoadCard();
			}
		});
	}

	private ObservableList<PropertySheet.Item> updateCardProperties(Card card) {
		ObservableList<PropertySheet.Item> items = FXCollections.observableArrayList();
		for (Block<?> block : card.getBlocks()) {
			if (block.getId() == null) {
				continue;
			}
			try {
				if (block instanceof TextBlock) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor("text", TextBlock.class);
					propertyDescriptor.setDisplayName(block.getId());
					propertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
					items.add(new BeanProperty(block, propertyDescriptor));

				} else if (block instanceof ImageBlock) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor("image", ImageBlock.class);
					propertyDescriptor.setDisplayName(block.getId());
					propertyDescriptor.setPropertyEditorClass(ImageSelectionEditor.class);
					propertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
					items.add(new BeanProperty(block, propertyDescriptor));

				} else {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor("value", Block.class) {
						@Override
						public Class<?> getPropertyType() {
							return String.class;
						}
					};
					propertyDescriptor.setDisplayName(block.getId());
					propertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
					items.add(new BeanProperty(block, propertyDescriptor));
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	private ObservableList<PropertySheet.Item> updateTemplateProperties(Card card) {
		ObservableList<PropertySheet.Item> items = FXCollections.observableArrayList();
		for (Block<?> block : card.getBlocks()) {
			if (block.getId() == null) {
				continue;
			}
			try {
				//Translations
				PropertyDescriptor translateX = new PropertyDescriptor("translateX", TextBlock.class);
				translateX.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
				items.add(new BeanProperty(block, translateX));

				PropertyDescriptor translateY = new PropertyDescriptor("translateY", TextBlock.class);
				translateY.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
				items.add(new BeanProperty(block, translateY));

				PropertyDescriptor translateZ = new PropertyDescriptor("translateZ", TextBlock.class);
				translateZ.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
				items.add(new BeanProperty(block, translateZ));

				//Anchors
				PropertyDescriptor top = new PropertyDescriptor("top", TextBlock.class);
				top.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
				items.add(new BeanProperty(block, top));

				PropertyDescriptor right = new PropertyDescriptor("right", TextBlock.class);
				right.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
				items.add(new BeanProperty(block, right));

				PropertyDescriptor bottom = new PropertyDescriptor("bottom", TextBlock.class);
				bottom.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
				items.add(new BeanProperty(block, bottom));

				PropertyDescriptor left = new PropertyDescriptor("left", TextBlock.class);
				left.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
				items.add(new BeanProperty(block, left));
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	@FXML
	private void promptLoadCard() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(Path.of("cards").toFile());
		File file = fileChooser.showOpenDialog(this);
		if (file != null) {
			try {
				FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
				Card card = loader.load();
				editor.setCard(card);
				cardProperties.getItems().setAll(updateCardProperties(card));
				templateProperties.getItems().setAll(updateTemplateProperties(card));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@FXML
	private void promptSaveCard() {
		FileChooser fileChooser = new FileChooser();
		File wd = Path.of("").toAbsolutePath().toFile();
		File base = cardSelector.getFile();
		fileChooser.setInitialDirectory(base.isDirectory() ? base : wd);
		File file = fileChooser.showSaveDialog(this);
		if (file != null) {
			FXMLSaver.save(file, editor.getCard());
		}
	}

	public void loadTemplate(File file) {
		try {
			FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
			Card template = loader.load();
			ObservableList<PropertySheet.Item> properties = updateTemplateProperties(template);
			templateProperties.getItems().setAll(properties);

			editor.setCard(template);
			sizeToScene();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	public void openPolyglotConsole() {
		if (consoleStage == null) {
			consoleStage = new Stage();
			consoleStage.setTitle("Polyglot Console");
			PolyglotConsole console = new PolyglotConsole();
			consoleStage.setScene(new Scene(console));
			Context context = console.getContext();
			context.getPolyglotBindings().putMember("card", editor.getCard());
			console.setPrefSize(600, 400);
			editor.cardProperty().addListener((observable, oldValue, newValue) -> {
				if (oldValue != null) {
					context.getPolyglotBindings().removeMember("card");
				}
				if (newValue != null) {
					context.getPolyglotBindings().putMember("card", newValue);
				}
				console.refreshBindings();
			});
		}
		consoleStage.show();
		consoleStage.toFront();
	}
}