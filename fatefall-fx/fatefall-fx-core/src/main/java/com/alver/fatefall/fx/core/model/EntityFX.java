package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Entity;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.alver.fatefall.fx.core.view.EditorInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EntityFX implements Entity {

	private final Long id;
	private final StringProperty name = new SimpleStringProperty(this, "name");
	private final StringProperty json = new SimpleStringProperty(this, "json");
	private final TreeProperty<Object> data = new TreeProperty<>(this, "data");

	public EntityFX() {
		this(-1l);
	}
	public EntityFX(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	@EditorInfo(ignore = true)
	public StringProperty nameProperty() {
		return this.name;
	}
	public String getName() {
		return this.name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}

	@EditorInfo(ignore = true)
	public StringProperty jsonProperty() {
		return this.json;
	}
	public String getJson() {
		return this.json.get();
	}
	public void setJson(String json) {
		this.json.set(json);
	}

	@EditorInfo(ignore = true)
	public TreeProperty<Object> dataProperty() {
		return data;
	}
	public TreeProperty<Object> getData() {
		return data;
	}
	public void setData(TreeProperty<Object> data) {
		this.data.set(data);
		this.data.childrenMapProperty().clear();
		if (data != null) {
			this.data.childrenMapProperty().putAll(data.getChildrenMap());
		}
	}

}
