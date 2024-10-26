package com.alver.fatefall.fx.core.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class EditorFactory {

	@SuppressWarnings("unchecked")
	public <T> Editor<T> buildEditor(PropertyInfo propertyInfo, Property<T> property) {
		String name = propertyInfo.displayName();
		if (propertyInfo.type().isEnum()) {
			T[] enums = (T[]) propertyInfo.type().getEnumConstants();
			return new SelectionEditor<>(name, property, FXCollections.observableArrayList(enums));
		} else if (String.class.equals(propertyInfo.type())) {
			return (Editor<T>) new TextEditor(name, (StringProperty) property);
		} else if (Boolean.class.equals(propertyInfo.type())) {
			return (Editor<T>) new BooleanEditor(name, (BooleanProperty) property);
		} else if (Double.class.equals(propertyInfo.type())) {
			return (Editor<T>) new DoubleEditor(name, (DoubleProperty) property);
		} else if (Color.class.equals(propertyInfo.type())) {
			return (Editor<T>) new ColorSelectionEditor(name, (Property<Color>) property);
		} else if (Image.class.equals(propertyInfo.type())) {
			return (Editor<T>) new ImageSelectionEditor(name, (Property<Image>) property);
		} else {
			return new IntrospectingPropertyEditor<>(name, property);
		}
	}
}
