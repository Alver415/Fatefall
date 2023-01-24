package com.alver.fatefall.app.services.modal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Modal {

	protected final Stage stage;
	protected final Scene scene;
	protected final BorderPane root;
	protected final Label label;
	protected final HBox buttonBox;
	protected final Button cancelButton;
	protected final Button okButton;

	public Modal(String title, String message) {
		cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> close());
		okButton = new Button("Ok");
		okButton.setOnAction(e -> close());
		label = new Label(message);
		root = new BorderPane();
		root.setPadding(new Insets(20));
		root.setCenter(label);
		buttonBox = new HBox(cancelButton, okButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		root.setBottom(buttonBox);
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(title);
		stage.initModality(Modality.NONE);

	}

	public void show(){
		stage.show();
	}
	public void close(){
		stage.close();
	}
}
