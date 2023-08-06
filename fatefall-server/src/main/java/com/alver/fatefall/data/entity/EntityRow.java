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
	private String data;

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

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
