package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Skin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class BeanEditor<T> extends EditorControl<T> {

	private static final Logger log = LoggerFactory.getLogger(BeanEditor.class);

	private static final String STYLE_CLASS = "bean-editor";
	private static final String STYLE_SHEET = Objects.requireNonNull(
			BeanEditor.class.getResource("BeanEditor.css")).toExternalForm();

	public BeanEditor(String name, Property<T> property) {
		super(name, property);
		getStyleClass().add(STYLE_CLASS);
		getStylesheets().add(STYLE_SHEET);
	}

	private final ListProperty<EditorControl<?>> editorControls = new SimpleListProperty<>(
			this, "editorControls", FXCollections.observableArrayList());

	public ListProperty<EditorControl<?>> editorControlsProperty() {
		return this.editorControls;
	}

	public ObservableList<EditorControl<?>> getEditorControls() {
		return this.editorControlsProperty().get();
	}

	public void setEditorControls(ObservableList<EditorControl<?>> value) {
		this.editorControlsProperty().set(value);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new BeanEditorSkinBase<>(this) {};
	}
}
