package com.alver.fatefall.fx.core.view;

import com.alver.fsfx.util.Converter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;

public class DoubleEditor extends BaseEditor<Double> {

	private final TextField textField;

	DoubleEditor(String name, DoubleProperty property) {
		super(name);
		this.textField = new TextField();

		Property<Double> doubleProperty = new SimpleObjectProperty<>();
		Converter<Double, Number> converter = Converter.of(d -> d, n -> (Double) n);
		converter.bindBidirectional(doubleProperty, property);

		doubleProperty.setValue(0d);
		textField.setText("0");
		Converter.of(String::valueOf, Double::parseDouble).bindBidirectional(doubleProperty, textField.textProperty());

		textField.textProperty().addListener((_, oldValue, newValue) -> {

			if (newValue == null){
				textField.setText("");
				return;
			}
			try {
				double doubleValue = Double.parseDouble(newValue);
				if (doubleValue > 10){
					textField.setText("10");
				} else if (doubleValue < 0){
					textField.setText("0");
				}
			} catch (NumberFormatException e){
				textField.setText(oldValue);
			}
		});

		setNode(textField);
	}
}
