package com.alver.fatefall.fx.app.view.entity.card.template;


import javafx.beans.property.Property;

public interface TemplateController<M> {

	Property<M> modelProperty();

	default M getModel() {
		return this.modelProperty().getValue();
	}

	default void setModel(M value) {
		this.modelProperty().setValue(value);
	}
}
