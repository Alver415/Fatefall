package com.alver.fatefall.app.fx.entity;

import com.alver.fatefall.data.entity.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EntityFX implements Entity {

	private final Long id;
	private final StringProperty name = new SimpleStringProperty(this, "name");
	private final StringProperty data = new SimpleStringProperty(this, "data");

	public EntityFX() {
		this(null);
	}
	public EntityFX(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public StringProperty nameProperty(){
		return this.name;
	}
	public String getName() {
		return this.name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty dataProperty(){
		return this.data;
	}
	public String getData() {
		return this.data.get();
	}
	public void setData(String data) {
		this.data.set(data);
	}

}
