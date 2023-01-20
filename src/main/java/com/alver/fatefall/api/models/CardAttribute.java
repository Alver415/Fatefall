package com.alver.fatefall.api.models;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CardAttribute<T> {

	protected StringProperty id = new SimpleStringProperty();
	protected StringProperty name = new SimpleStringProperty();
	protected StringProperty data = new SimpleStringProperty();
	protected Property<T> property = new SimpleObjectProperty<>();

	public String getId() {
		return id.get();
	}
	public StringProperty idProperty() {
		return id;
	}
	public void setId(String id) {
		this.id.set(id);
	}
	public String getName() {
		return name.get();
	}
	public StringProperty nameProperty() {
		return name;
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getData() {
		return data.get();
	}
	public StringProperty dataProperty() {
		return data;
	}
	public void setData(String data) {
		this.data.set(data);
	}
	public Property<T> getProperty() {
		return property;
	}
	public Property<T> propertyProperty() {
		return property;
	}
	public void setProperty(Property<T> property) {
		this.property = property;
	}
}
