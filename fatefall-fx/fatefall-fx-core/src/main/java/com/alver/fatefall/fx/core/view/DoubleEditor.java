package com.alver.fatefall.fx.core.view;

import com.alver.fsfx.util.Converter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class DoubleEditor extends BaseEditor<Double> {

	private final TextField textField;

	static List list = new ArrayList();

	DoubleEditor(String name, DoubleProperty property) {
		super(name);
		this.textField = new TextField();

		Property<Double> doubleProperty = new SimpleObjectProperty<>();
		list.add(doubleProperty);
		Converter<Double, Number> converter = Converter.of(d -> d, n -> (Double) n);
		converter.bindBidirectional(doubleProperty, property);

		doubleProperty.setValue(property.get());
		textField.setText(String.valueOf(doubleProperty.getValue()));
		Converter.of(String::valueOf, Double::parseDouble).bindBidirectional(doubleProperty, textField.textProperty());

		setNode(textField);
	}
}
