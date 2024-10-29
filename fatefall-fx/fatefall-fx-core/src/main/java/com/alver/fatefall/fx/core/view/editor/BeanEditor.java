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

	public BeanEditor(Property<T> property) {
		super(property);
		getStyleClass().add(STYLE_CLASS);
		getStylesheets().add(STYLE_SHEET);
	}

	private final ListProperty<Editor<?, ?>> editors = new SimpleListProperty<>(
			this, "editors", FXCollections.observableArrayList());

	public ListProperty<Editor<?, ?>> editorsProperty() {
		return this.editors;
	}

	public ObservableList<Editor<?, ?>> getEditors() {
		return this.editorsProperty().get();
	}

	public void setEditors(ObservableList<Editor<?, ?>> value) {
		this.editorsProperty().set(value);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new BeanEditorSkinBase<>(this) {};
	}
}
