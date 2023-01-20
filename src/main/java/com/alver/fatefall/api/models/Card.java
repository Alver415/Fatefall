package com.alver.fatefall.api.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Card {

	protected StringProperty id = new SimpleStringProperty();
	protected StringProperty name = new SimpleStringProperty();
	protected StringProperty frontUrl = new SimpleStringProperty();
	protected StringProperty backUrl = new SimpleStringProperty();
	protected ListProperty<CardAttribute<?>> attributeList = new SimpleListProperty<>(FXCollections.observableArrayList());
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
	public String getFrontUrl() {
		return frontUrl.get();
	}
	public StringProperty frontUrlProperty() {
		return frontUrl;
	}
	public void setFrontUrl(String frontUrl) {
		this.frontUrl.set(frontUrl);
	}
	public String getBackUrl() {
		return backUrl.get();
	}
	public StringProperty backUrlProperty() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl.set(backUrl);
	}
	public ObservableList<CardAttribute<?>> getAttributeList() {
		return attributeList.get();
	}
	public ListProperty<CardAttribute<?>> attributeListProperty() {
		return attributeList;
	}
	public void setAttributeList(ObservableList<CardAttribute<?>> attributeList) {
		this.attributeList.set(attributeList);
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
