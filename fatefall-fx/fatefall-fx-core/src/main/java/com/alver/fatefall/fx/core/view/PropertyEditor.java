package com.alver.fatefall.fx.core.view;

import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class PropertyEditor<T> extends EditorControl<T> {

	private static final Logger log = LoggerFactory.getLogger(PropertyEditor.class);
	private static final String CSS = Objects.requireNonNull(PropertyEditor.class.getResource("PropertyEditor.css"))
			.toExternalForm();

	public PropertyEditor(String name, Property<T> property) {
		super(name, property);
		getStylesheets().add(CSS);
	}

	private final ListProperty<EditorControl<?>> editors = new SimpleListProperty<>(
			this, "editors", FXCollections.observableArrayList());

	public ListProperty<EditorControl<?>> editorsProperty() {
		return this.editors;
	}

	public ObservableList<EditorControl<?>> getEditors() {
		return this.editorsProperty().get();
	}

	public void setEditors(ObservableList<EditorControl<?>> value) {
		this.editorsProperty().set(value);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new TopLabeledSkin();
	}

	private class TopLabeledSkin extends SkinBase<PropertyEditor<?>> {

		private TopLabeledSkin() {
			super(PropertyEditor.this);

			VBox vbox = new VBox();
			for (EditorControl<?> editor : getEditors()) {
				Label name = new Label();
				name.textProperty().bind(editor.nameProperty());
				vbox.getChildren().add(new VBox(name, editor));
			}

			ScrollPane scrollPane = new ScrollPane(vbox);
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			getChildren().setAll(scrollPane);
		}

	}

	private class LeftLabeledSkin extends SkinBase<PropertyEditor<?>> {

		private LeftLabeledSkin() {
			super(PropertyEditor.this);

			Insets insets = new Insets(5, 10, 5, 10);
			VBox vbox = new VBox();
			for (EditorControl<?> editor : getEditors()) {
				Label name = new Label();
				name.textProperty().bind(editor.nameProperty());
				HBox hbox = new HBox(name, editor);
				HBox.setMargin(name, insets);
				HBox.setHgrow(name, Priority.NEVER);
				HBox.setHgrow(editor, Priority.ALWAYS);
				VBox.setMargin(hbox, insets);
				vbox.getChildren().add(hbox);

			}

			ScrollPane scrollPane = new ScrollPane(vbox);
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			getChildren().setAll(scrollPane);
		}

	}

	private class GridLabeledSkin extends SkinBase<PropertyEditor<?>> {

		private GridLabeledSkin() {
			super(PropertyEditor.this);

			GridPane gridPane = new GridPane(10, 10);
			int row = 0;
			for (EditorControl<?> editor : getEditors()) {
				Label label = new Label();
				label.textProperty().bind(editor.nameProperty());
				gridPane.add(label, 0, row, 1, 1);
				gridPane.add(editor, 1, row, 3, 1);
				row++;
			}

			AnchorPane anchorPane = new AnchorPane(gridPane);
			AnchorPane.setLeftAnchor(gridPane, 0d);
			AnchorPane.setRightAnchor(gridPane, 0d);
			AnchorPane.setTopAnchor(gridPane, 0d);
			AnchorPane.setBottomAnchor(gridPane, 0d);


			ScrollPane scrollPane = new ScrollPane(anchorPane);
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			getChildren().setAll(scrollPane);
		}

	}


}
