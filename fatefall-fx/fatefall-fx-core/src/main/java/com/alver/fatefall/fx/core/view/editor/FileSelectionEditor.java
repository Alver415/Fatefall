package com.alver.fatefall.fx.core.view.editor;

import com.alver.fsfx.util.Converter;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileSelectionEditor extends EditorControl<File> {

	private static final Logger log = LoggerFactory.getLogger(FileSelectionEditor.class);

	public FileSelectionEditor(Property<File> property) {
		super(property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new DialogSkin();
	}

	private class DialogSkin extends SkinBase<FileSelectionEditor> {

		protected DialogSkin() {
			super(FileSelectionEditor.this);
			TextField textField = new TextField();
			textField.setEditable(false);

			Button openButton = new Button("...");
			openButton.setOnAction(a -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose a File");
				fileChooser.setInitialDirectory(new File("."));
				File file = fileChooser.showOpenDialog(getScene().getWindow());
				getProperty().setValue(file);
			});

			Converter<String, File> convert = Converter.of(this::toFile, this::fromFile);
			convert.bindBidirectional(textField.textProperty(), getProperty());
			AnchorPane anchorPane = new AnchorPane(textField, openButton);
			anchor(textField, 0d);
			AnchorPane.setTopAnchor(openButton, 3d);
			AnchorPane.setRightAnchor(openButton, 3d);
			AnchorPane.setBottomAnchor(openButton, 3d);
			getChildren().setAll(anchorPane);
		}

		private File toFile(String url) {
			return url == null ? null : new File(url);
		}

		private String fromFile(File file) {
			return file == null ? null : file.toString();
		}
	}

	private static void anchor(Node node, Double value) {
		AnchorPane.setLeftAnchor(node, value);
		AnchorPane.setTopAnchor(node, value);
		AnchorPane.setRightAnchor(node, value);
		AnchorPane.setBottomAnchor(node, value);
	}
}
