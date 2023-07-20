package com.alver.fatefall.data.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersistedEntity {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	public Long getId() {
		return id;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
	private List<Field> fields = new ArrayList<>();

	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
