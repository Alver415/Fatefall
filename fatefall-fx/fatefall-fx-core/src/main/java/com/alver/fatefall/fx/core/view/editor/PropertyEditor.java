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

public class PropertyEditor<T> extends EditorControl<T> {

	private static final Logger log = LoggerFactory.getLogger(PropertyEditor.class);

	private static final String STYLE_CLASS = "property-editor";
	private static final String STYLE_SHEET = Objects.requireNonNull(
			PropertyEditor.class.getResource("PropertyEditor.css")).toExternalForm();

	public PropertyEditor(String name, Property<T> property) {
		super(name, property);
		getStyleClass().add(STYLE_CLASS);
		getStylesheets().add(STYLE_SHEET);
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
		return new TopLabeledSkin(this);
	}
}
