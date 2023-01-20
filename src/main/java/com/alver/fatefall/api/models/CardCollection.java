package com.alver.fatefall.api.models;


import jakarta.persistence.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

public class CardCollection {

    protected StringProperty id = new SimpleStringProperty();
    protected StringProperty name = new SimpleStringProperty();
    protected ListProperty<Card> cardList = new SimpleListProperty<>(FXCollections.observableArrayList());
    protected StringProperty data = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }
    public StringProperty idProperty() {
        return id;
    }
    public void setId(String id) {
        this.id.set(id);
    }
    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public ObservableList<Card> getCardList() {
        return cardList.get();
    }
    public ListProperty<Card> cardListProperty() {
        return cardList;
    }
    public void setCardList(ObservableList<Card> cardList) {
        this.cardList.set(cardList);
    }
    public String getData() {
        return data.get();
    }
    public StringProperty dataProperty() {
        return data;
    }
    public void setData(String data) {
        this.data.set(data);
    }
}
