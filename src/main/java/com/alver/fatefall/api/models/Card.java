package com.alver.fatefall.api.models;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "card")
public class Card {

    protected String id;
    protected String name;
    protected String frontUrl;
    protected String backUrl;
    protected StringProperty dataProperty = new SimpleStringProperty(this, "data");

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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

    @Lob
    public String getData() {
        return dataProperty.get();
    }

    public void setData(String data) {
        this.dataProperty.set(data);
    }
    public StringProperty dataProperty(){
        return dataProperty;
    }
}
