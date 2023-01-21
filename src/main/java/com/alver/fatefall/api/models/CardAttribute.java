package com.alver.fatefall.api.models;

import com.alver.fatefall.app.AbstractCardAttribute;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CardAttribute<T> extends AbstractCardAttribute {

	protected StringProperty id = new SimpleStringProperty();
	protected StringProperty name = new SimpleStringProperty();
	protected ObjectProperty<Class<T>> type = new SimpleObjectProperty<>();
	protected Property<T> property = new SimpleObjectProperty<>();
	protected ListProperty<CardAttribute> children = new SimpleListProperty<>(FXCollections.observableArrayList());
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
	@Override
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

	public ObservableList<CardAttribute> getChildren() {
		return children.get();
	}
	public ListProperty<CardAttribute> childrenProperty() {
		return children;
	}
	public void setChildren(ObservableList<CardAttribute> children) {
		this.children.set(children);
	}
}
