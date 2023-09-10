package com.alver.fatefall.data.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class EntityRow implements Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Lob
	@Column
	private String json;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}

}
