package com.alver.fatefall.app.services.fileselector;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class FileSelector {
	private static final String STYLESHEET = Objects.requireNonNull(FileSelector.class.getResource("file-selector.css")).toExternalForm();
	private static final String LABEL_TEXT = "Click or Drag and Drop File";

	protected final Stage stage;
	protected final Scene scene;
	protected final BorderPane root;
	protected final Label label;
	protected final HBox buttonBox;
	protected final Button cancelButton;
	protected final Button submitButton;
	protected Consumer<File> onFileSubmitted;
	protected ObjectProperty<File> selectedFileProperty;

	public FileSelector(Consumer<File> onFileSubmitted) {
		this.onFileSubmitted = onFileSubmitted;
		this.selectedFileProperty = new SimpleObjectProperty<>();

		cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> close());
		submitButton = new Button("Submit");
		submitButton.setOnAction(e -> submit());
		submitButton.disableProperty().bind(Bindings.isNull(selectedFileProperty));
		label = new Label(LABEL_TEXT);
		label.getStyleClass().add("file-selector");
		label.setWrapText(true);
		root = new BorderPane();
		root.setPadding(new Insets(20));
		root.setCenter(label);
		buttonBox = new HBox(cancelButton, submitButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		root.setBottom(buttonBox);
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("File Selector");
		stage.initModality(Modality.NONE);

		scene.getStylesheets().add(STYLESHEET);
		label.getStyleClass().add("file-selector");

		label.addEventHandler(DragEvent.DRAG_ENTERED, event -> {
			if (event.getGestureSource() != label && event.getDragboard().hasFiles()) {
				label.getStyleClass().add("file-drag");
			}
			event.consume();
		});
		label.addEventHandler(DragEvent.DRAG_EXITED, event -> {
			if (event.getGestureSource() != label && event.getDragboard().hasFiles()) {
				label.getStyleClass().removeAll("file-drag");
			}
			event.consume();
		});

		selectedFileProperty.addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				label.setText(newValue.getName());
			} else {
				label.setText(LABEL_TEXT);
			}
		});
	}

	public void submit() {
		onFileSubmitted.accept(selectedFileProperty.get());
		close();
	}

	public abstract void show();
	public abstract void close();
}
