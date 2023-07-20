package com.alver.fatefall.data.entity;

import java.util.LinkedHashMap;
import java.util.Map;

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
	public void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}

}
