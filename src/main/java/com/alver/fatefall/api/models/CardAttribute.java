package com.alver.fatefall.api.models;

import javafx.beans.property.*;

public class CardAttribute<T> {

	protected StringProperty id = new SimpleStringProperty();
	protected StringProperty name = new SimpleStringProperty();
	protected ObjectProperty<Class<T>> type = new SimpleObjectProperty<>();
	protected Property<T> property = new SimpleObjectProperty<>();
	protected StringProperty data = new SimpleStringProperty();

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
	public Class<T> getType() {
		return type.get();
	}
	public ObjectProperty<Class<T>> typeProperty() {
		return type;
	}
	public void setType(Class<T> type) {
		this.type.set(type);
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
	public String getData() {
		return data.get();
	}
	public StringProperty dataProperty() {
		return data;
	}
	public void setData(String data) {
		this.data.set(data);
	}

}
