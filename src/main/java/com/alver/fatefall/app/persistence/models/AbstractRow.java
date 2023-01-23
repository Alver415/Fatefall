package com.alver.fatefall.app.persistence.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class AbstractRow<T> {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AttributeRow> attributes = new ArrayList<>();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AttributeRow> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<AttributeRow> attributes) {
		this.attributes = attributes;
	}
}