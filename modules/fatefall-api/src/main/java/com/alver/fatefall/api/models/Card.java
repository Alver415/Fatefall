package com.alver.fatefall.api.models;

import com.alver.fatefall.api.PersistedObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.persistence.*;

@Entity
public class Card extends PersistedObject {

    @Transient
    private ObjectNode data;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CardImage frontFace;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CardImage backFace;

    public ObjectNode getData() {
        //Ensure data is never null, but rather empty.
        if (data == null) {
            data = JsonNodeFactory.instance.objectNode();
        }
        return data;
    }
    public void setData(ObjectNode data) {
        this.data = data;
    }

    @Access(AccessType.PROPERTY)
    @Column(name="data", length = Integer.MAX_VALUE)
    public String getJson(){
        return data.toString();
    }
    public String getJsonPretty() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void setJson(String json) throws JsonProcessingException {
        this.data = (ObjectNode) new ObjectMapper().readTree(json);
    }

    public CardImage getFrontFace() {
        return frontFace;
    }
    public void setFrontFace(CardImage frontFace) {
        this.frontFace = frontFace;
    }
    public CardImage getBackFace() {
        return backFace;
    }
    public void setBackFace(CardImage backFace) {
        this.backFace = backFace;
    }
}
