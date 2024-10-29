package com.alver.fatefall.fx.core.view;

import com.alver.fatefall.fx.core.view.editor.EditorInfo;
import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class Example {
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

	private final StringProperty description = new SimpleStringProperty(this, "description");

	@EditorInfo(displayName = "Description", category = "General")
	public StringProperty descriptionProperty() {
		return this.description;
	}

	public String getDescription() {
		return this.descriptionProperty().get();
	}

	public void setDescription(String value) {
		this.descriptionProperty().set(value);
	}

	private final DoubleProperty age = new SimpleDoubleProperty(this, "age");

	public DoubleProperty ageProperty() {
		return this.age;
	}

	public Double getAge() {
		return this.ageProperty().get();
	}

	public void setAge(Double value) {
		this.ageProperty().set(value);
	}

	private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>(this, "direction");

	public ObjectProperty<Direction> directionProperty() {
		return this.direction;
	}

	public Direction getDirection() {
		return this.directionProperty().get();
	}

	public void setDirection(Direction value) {
		this.directionProperty().set(value);
	}

	private final ObjectProperty<Color> color = new SimpleObjectProperty<>(this, "color");

	public ObjectProperty<Color> colorProperty() {
		return this.color;
	}

	public Color getColor() {
		return this.colorProperty().get();
	}

	public void setColor(Color value) {
		this.colorProperty().set(value);
	}

	private final ObjectProperty<Example> child = new SimpleObjectProperty<>(this, "child");

	public ObjectProperty<Example> childProperty() {
		return this.child;
	}

	public Example getChild() {
		return this.childProperty().get();
	}

	public void setChild(Example value) {
		this.childProperty().set(value);
	}

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
}