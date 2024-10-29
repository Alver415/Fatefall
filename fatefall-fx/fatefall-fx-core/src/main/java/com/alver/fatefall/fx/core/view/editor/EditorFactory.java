package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

public class EditorFactory {

	@SuppressWarnings("unchecked")
	public <T> EditorControl<T> buildEditor(PropertyInfo propertyInfo, Property<T> property) {
		String name = propertyInfo.displayName();
		EditorControl<T> editor;
		if (propertyInfo.type().isEnum()) {
			T[] enums = (T[]) propertyInfo.type().getEnumConstants();
			editor =  new SelectionEditor<>(name, property, FXCollections.observableArrayList(enums));
		} else if (String.class.equals(propertyInfo.type())) {
			editor =  (EditorControl<T>) new TextEditor(name, (StringProperty) property);
		} else if (Boolean.class.equals(propertyInfo.type())) {
			editor =  (EditorControl<T>) new BooleanEditor(name, (BooleanProperty) property);
		} else if (Double.class.equals(propertyInfo.type())) {
			editor =  (EditorControl<T>) new DoubleEditor(name, (DoubleProperty) property);
		} else if (Integer.class.equals(propertyInfo.type())) {
			editor =  (EditorControl<T>) new IntegerEditor(name, (IntegerProperty) property);
		} else if (Color.class.equals(propertyInfo.type())) {
			editor =  (EditorControl<T>) new ColorSelectionEditor(name, (Property<Color>) property);
		} else if (Image.class.equals(propertyInfo.type())) {
			editor =  (EditorControl<T>) new ImageSelectionEditor(name, (Property<Image>) property);
		} else if (File.class.equals(propertyInfo.type())) {
			editor =  (EditorControl<T>) new FileSelectionEditor(name, (Property<File>) property);
		} else {
			editor =  new IntrospectingPropertyEditor<>(name, property);
		}
		return editor;
	}
}
