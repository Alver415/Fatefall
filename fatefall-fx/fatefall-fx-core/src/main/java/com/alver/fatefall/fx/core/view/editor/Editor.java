package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Editor<T, C extends EditorControl<T>> extends Control {

	public Editor(Property<String> name, EditorControl<T> control) {
		nameProperty().bind(name);
		controlProperty().set(control);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new EditorSkinBase<>(this);
	}

	public static class EditorSkinBase<T, C extends EditorControl<T>> extends SkinBase<Editor<T, C>> {

		private static final String STYLE_CLASS = "editor";
		private static final String STYLE_SHEET = Objects.requireNonNull(
				Editor.class.getResource("Editor.css")).toExternalForm();

		protected EditorSkinBase(Editor<T, C> editor) {
			super(editor);
			editor.getStyleClass().add(STYLE_CLASS);
			editor.getStylesheets().add(STYLE_SHEET);

			Label label = new Label();
			label.textProperty().bind(editor.nameProperty());
			HBox hBox = new HBox(editor.getControl());
			HBox.setHgrow(editor.getControl(), Priority.ALWAYS);
			VBox vBox = new VBox(label, hBox);
			getChildren().setAll(vBox);
		}
	}

	private final StringProperty name = new SimpleStringProperty(this, "name");

	public StringProperty nameProperty() {
		return this.name;
	}

	public String getName() {
		return this.nameProperty().get();
	}

	public void setName(String value) {
		this.nameProperty().set(value);
	}

	private final ObjectProperty<EditorControl<T>> control = new SimpleObjectProperty<>(this, "control");

	public ObjectProperty<EditorControl<T>> controlProperty() {
		return this.control;
	}

	public EditorControl<T> getControl() {
		return this.controlProperty().get();
	}

	public void setControl(EditorControl<T> value) {
		this.controlProperty().set(value);
	}

}
