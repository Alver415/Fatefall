package com.alver.fatefall.app.services.fileselector;

import com.alver.fatefall.app.Prototype;
import com.jpro.webapi.WebAPI;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.function.Consumer;

public class FileSelectorImpl extends FileSelector {

	private final FileChooser fileChooser;

	public FileSelectorImpl(Consumer<File> onFileSubmitted) {
		super(onFileSubmitted);
		fileChooser = new FileChooser();
		fileChooser.setTitle("File Selector");

		label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {
				selectedFileProperty.set(file);
			}
		});

		label.addEventHandler(DragEvent.DRAG_OVER, event -> {
			if (event.getGestureSource() != label && event.getDragboard().hasFiles()) {
				event.acceptTransferModes(TransferMode.ANY);
			}
			event.consume();
		});

		label.addEventHandler(DragEvent.DRAG_DROPPED, event -> {
			Dragboard dragboard = event.getDragboard();
			if (dragboard.hasFiles()) {
				selectedFileProperty.set(dragboard.getFiles().get(0));
				event.setDropCompleted(true);
			}
			event.consume();
		});
	}

	@Override
	public void show() {
		stage.show();
	}

	@Override
	public void close() {
		stage.close();
	}
}
