package com.alver.fatefall.data.entity;

import java.util.*;

public abstract class Entity {

	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Map<String, Field> fields = new LinkedHashMap<>();

	public Map<String, Field> getFields() {
		return fields;
	}
	private void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}

	public void addField(Field field) {
		addFields(Map.of(field.getName(), field));
	}
	public void addField(String key, Field field) {
		addFields(Map.of(key, field));
	}
	public void addFields(Map<String,Field> fields) {
		this.fields.putAll(fields);
	}
	public void removeField(String key) {
		this.fields.remove(key);
	}

}
