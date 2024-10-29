package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

public class EditorControlFactory {

	@SuppressWarnings("unchecked")
	public <T> EditorControl<T> buildEditorControl(PropertyInfo propertyInfo, Property<T> property) {
		EditorControl<T> editor;
		if (propertyInfo.type().isEnum()) {
			T[] enums = (T[]) propertyInfo.type().getEnumConstants();
			editor = new SelectionEditor<>(property, FXCollections.observableArrayList(enums));
		} else if (String.class.equals(propertyInfo.type())) {
			editor = (EditorControl<T>) new TextEditor((StringProperty) property);
		} else if (Boolean.class.equals(propertyInfo.type())) {
			editor = (EditorControl<T>) new BooleanEditor((BooleanProperty) property);
		} else if (Double.class.equals(propertyInfo.type())) {
			editor = (EditorControl<T>) new DoubleEditor((DoubleProperty) property);
		} else if (Integer.class.equals(propertyInfo.type())) {
			editor = (EditorControl<T>) new IntegerEditor((IntegerProperty) property);
		} else if (Color.class.equals(propertyInfo.type())) {
			editor = (EditorControl<T>) new ColorSelectionEditor((Property<Color>) property);
		} else if (Image.class.equals(propertyInfo.type())) {
			editor = (EditorControl<T>) new ImageSelectionEditor((Property<Image>) property);
		} else if (File.class.equals(propertyInfo.type())) {
			editor = (EditorControl<T>) new FileSelectionEditor((Property<File>) property);
		} else {
			editor = new IntrospectingBeanEditor<>(property);
		}
		return editor;
	}
}
