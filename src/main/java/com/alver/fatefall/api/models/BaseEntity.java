package com.alver.fatefall.api.models;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public abstract class BaseEntity {

	protected String id;
	protected StringProperty name = new SimpleStringProperty();
	protected Map<String, Element> elements = new HashMap<>();

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}

	public void addElement(Element attribute) {
		elements.put(attribute.getName(), attribute);
	}

	private static final String DELIMITER = "\\.";

	public Element getElement(String name) {
		String[] split = name.split(DELIMITER);
		if (split.length == 0) {
			return null;
		} else if (elements.containsKey(split[0])) {
			if (split.length == 1) {
				return elements.get(split[0]);
			} else {
				return elements.get(split[0]).getElement(name.substring(name.indexOf(DELIMITER) + 1));
			}
		} else {
			return null;
		}
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Map<String, Element> getElement() {
		return elements;
	}

	public void setElements(Map<String, Element> elements) {
		this.elements = elements;
	}

}
