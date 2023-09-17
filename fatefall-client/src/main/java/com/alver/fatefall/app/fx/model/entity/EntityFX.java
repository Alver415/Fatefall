package com.alver.fatefall.app.fx.model.entity;

import com.alver.fatefall.property.TreeProperty;
import com.alver.fatefall.data.entity.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EntityFX implements Entity {

	private final Long id;
	private final StringProperty name = new SimpleStringProperty(this, "name");
	private final StringProperty json = new SimpleStringProperty(this, "json");
	private TreeProperty<Object> data;

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

	public StringProperty jsonProperty(){
		return this.json;
	}
	public String getJson() {
		return this.json.get();
	}
	public void setJson(String json) {
		this.json.set(json);
	}


	public TreeProperty<Object> dataProperty() {
		return data;
	}
	public TreeProperty<Object> getData() {
		return data;
	}
	public void setData(TreeProperty<Object> data) {
		this.data = data;
	}


}
