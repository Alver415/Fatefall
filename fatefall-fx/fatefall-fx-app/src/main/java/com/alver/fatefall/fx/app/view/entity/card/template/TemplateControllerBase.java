package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TemplateControllerBase<T> implements TemplateController<T> {

	@Autowired
	public TemplateControllerBase(FatefallProperties properties) {
		widthProperty().bind(properties.getCardBaseWidth());
		heightProperty().bind(properties.getCardBaseHeight());
		arcWidthProperty().bind(properties.getCardBaseArcWidth());
		arcHeightProperty().bind(properties.getCardBaseArcHeight());
	}

	private final DoubleProperty width = new SimpleDoubleProperty(this, "width");

	public DoubleProperty widthProperty() {
		return this.width;
	}

	public Double getWidth() {
		return this.widthProperty().get();
	}

	public void setWidth(Double value) {
		this.widthProperty().set(value);
	}

	private final DoubleProperty height = new SimpleDoubleProperty(this, "height");

	public DoubleProperty heightProperty() {
		return this.height;
	}

	public Double getHeight() {
		return this.heightProperty().get();
	}

	public void setHeight(Double value) {
		this.heightProperty().set(value);
	}

	private final DoubleProperty arcWidth = new SimpleDoubleProperty(this, "arcWidth");

	public DoubleProperty arcWidthProperty() {
		return this.arcWidth;
	}

	public Double getArcWidth() {
		return this.arcWidthProperty().get();
	}

	public void setArcWidth(Double value) {
		this.arcWidthProperty().set(value);
	}

	private final DoubleProperty arcHeight = new SimpleDoubleProperty(this, "arcHeight");

	public DoubleProperty arcHeightProperty() {
		return this.arcHeight;
	}

	public Double getArcHeight() {
		return this.arcHeightProperty().get();
	}

	public void setArcHeight(Double value) {
		this.arcHeightProperty().set(value);
	}

}
