package com.alver.fatefall.app.persistence.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "card_attribute")
public class CardAttributeRow {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String type;
	@Lob
	private String value;
	@Lob
	private String data;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CardAttributeRow> children;

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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public List<CardAttributeRow> getChildren() {
		return children;
	}
	public void setChildren(List<CardAttributeRow> children) {
		this.children = children;
	}
}
