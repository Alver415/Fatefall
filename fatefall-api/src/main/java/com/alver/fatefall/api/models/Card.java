package com.alver.fatefall.api.models;

import com.alver.fatefall.api.PersistedObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import javax.persistence.*;

@Entity
public class Card extends PersistedObject {

    @Transient
    private JsonNode data;
    private String frontFaceUrl;
    private String backFaceUrl;
    private String fxml;

    @Access(AccessType.PROPERTY)
    @Column(name="fxml")
    public String getFxml(){
        return fxml;
    }
    public void setFxml(String fxml){
        this.fxml = fxml;
    }

    public JsonNode getData() {
        //Ensure data is never null, but rather empty.
        if (data == null) {
            data = JsonNodeFactory.instance.objectNode();
        }
        return data;
    }
    public void setData(JsonNode data) {
        this.data = data;
    }

    @Access(AccessType.PROPERTY)
    @Column(name="data", length = Integer.MAX_VALUE)
    public String getJson(){
        return getData().toString();
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

    public String getFrontFaceUrl() {
        return frontFaceUrl;
    }
    public void setFrontFaceUrl(String frontFaceUrl) {
        this.frontFaceUrl = frontFaceUrl;
    }
    public String getBackFaceUrl() {
        return backFaceUrl;
    }
    public void setBackFaceUrl(String backFaceUrl) {
        this.backFaceUrl = backFaceUrl;
    }


    @Transient
    protected ObjectProperty<Image> frontFaceImage = new SimpleObjectProperty<>(this, "frontFaceImage", null);
    public ObjectProperty<Image> frontFaceImageProperty(){
        return frontFaceImage;
    }
    public Image getFrontFaceImage(){
        return frontFaceImageProperty().get();
    }

    public void setFrontFaceImage(Image frontFaceImage) {
        this.frontFaceImageProperty().set(frontFaceImage);
    }

    @Transient
    protected ObjectProperty<Image> backFaceImage = new SimpleObjectProperty<>(this, "backFaceImage", null);
    public ObjectProperty<Image> backFaceImageProperty(){
        return backFaceImage;
    }
    public Image getBackFaceImage(){
        return backFaceImageProperty().get();
    }

    public void setBackFaceImage(Image backFaceImage) {
        this.backFaceImageProperty().set(backFaceImage);
    }
}
