package com.alver.fatefall.app.persistence.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "card")
public class CardRow {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String frontUrl;
	private String backUrl;
	@Lob
	private String data;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CardAttributeRow> attributeList;

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
	public String getFrontUrl() {
		return frontUrl;
	}
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<CardAttributeRow> getAttributeList() {
		return attributeList;
	}
	public void setAttributeList(List<CardAttributeRow> attributeList) {
		this.attributeList = attributeList;
	}
}
