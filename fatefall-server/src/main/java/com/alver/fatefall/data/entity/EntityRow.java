package com.alver.fatefall.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class EntityRow implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column
    protected String name;

    @Lob
    @Column
    @JsonIgnore
    protected String json;

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

    @JsonProperty
    public JsonNode getData() throws JsonProcessingException {
        return json == null ? null : new ObjectMapper().readTree(json);
    }

    @JsonProperty
    public void setData(JsonNode data) throws JsonProcessingException {
        this.json = data == null ? null : new ObjectMapper().writeValueAsString(data);
    }

}
