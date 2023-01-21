package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.CardAttribute;
import com.alver.fatefall.app.persistence.models.CardAttributeRow;
import javafx.beans.property.*;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class CardAttributeRepository implements CrudRepository<CardAttribute<?>, String> {

    @Autowired
    protected CardAttributeRowRepository cardAttributeRowRepository;

    @Override
    public <S extends CardAttribute<?>> S save(S entity) {
        CardAttributeRow row = toRow(entity);
        CardAttributeRow saved = cardAttributeRowRepository.save(row);
        return fromRow(saved);
    }

    @Override
    public <S extends CardAttribute<?>> Iterable<S> saveAll(Iterable<S> entities) {
        Iterable<CardAttributeRow> rows = toRows(entities);
        Iterable<CardAttributeRow> saved = cardAttributeRowRepository.saveAll(rows);
        return fromRows(saved);
    }

    @Override
    public Optional<CardAttribute<?>> findById(String id) {
        return cardAttributeRowRepository.findById(id).map(this::fromRow);
    }

    @Override
    public boolean existsById(String id) {
        return cardAttributeRowRepository.existsById(id);
    }

    @Override
    public Iterable<CardAttribute<?>> findAll() {
        return fromRows(cardAttributeRowRepository.findAll());
    }

    @Override
    public Iterable<CardAttribute<?>> findAllById(Iterable<String> strings) {
        return fromRows(cardAttributeRowRepository.findAllById(strings));
    }

    @Override
    public long count() {
        return cardAttributeRowRepository.count();
    }

    @Override
    public void deleteById(String id) {
        cardAttributeRowRepository.deleteById(id);
    }

    @Override
    public void delete(CardAttribute<?> entity) {
        cardAttributeRowRepository.delete(toRow(entity));
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        cardAttributeRowRepository.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends CardAttribute<?>> entities) {
        cardAttributeRowRepository.deleteAll(toRows(entities));
    }

    @Override
    public void deleteAll() {
        cardAttributeRowRepository.deleteAll();
    }

    <S extends CardAttribute<?>> CardAttributeRow toRow(S attribute) {
        CardAttributeRow row = new CardAttributeRow();
        row.setId(attribute.getId());
        row.setName(attribute.getName());
        row.setData(attribute.getData());
        row.setType(attribute.getType().getName());
        row.setValue(String.valueOf(attribute.getProperty().getValue()));
        row.setChildren(attribute.getChildren().stream()
                .map(this::toRow).toList());
        return row;
    }

    <S extends CardAttribute<?>> S fromRow(CardAttributeRow row) {
        CardAttribute<?> cardAttribute;
        String type = row.getType();
        if (Objects.equals(type, String.class.getName())) {
            CardAttribute<String> stringAttribute = new CardAttribute<>();
            stringAttribute.setProperty(new SimpleStringProperty(row.getValue()));
            stringAttribute.setType(String.class);
            cardAttribute = stringAttribute;
        } else if (Objects.equals(type, Double.class.getName())) {
            CardAttribute<Double> doubleAttribute = new CardAttribute<>();
            doubleAttribute.setProperty(new SimpleDoubleProperty(Double.parseDouble(row.getValue())).asObject());
            doubleAttribute.setType(Double.class);
            cardAttribute = doubleAttribute;
        } else if (Objects.equals(type, Integer.class.getName())) {
            CardAttribute<Integer> integerAttribute = new CardAttribute<>();
            integerAttribute.setProperty(new SimpleIntegerProperty(Integer.parseInt(row.getValue())).asObject());
            integerAttribute.setType(Integer.class);
            cardAttribute = integerAttribute;
        } else if (Objects.equals(type, Boolean.class.getName())) {
            CardAttribute<Boolean> booleanAttribute = new CardAttribute<>();
            booleanAttribute.setProperty(new SimpleBooleanProperty(Boolean.parseBoolean(row.getValue())));
            booleanAttribute.setType(Boolean.class);
            cardAttribute = booleanAttribute;
        } else if (Objects.equals(type, CardAttribute.class.getName())) {
            CardAttribute<CardAttribute> parentAttribute = new CardAttribute<>();
            parentAttribute.setProperty(new SimpleObjectProperty<>());
            parentAttribute.setType(CardAttribute.class);
            parentAttribute.childrenProperty().setAll(row.getChildren().stream().map(c -> (CardAttribute)fromRow(c)).toList());
            cardAttribute = parentAttribute;
        }else if (Objects.equals(type, List.class.getName())) {
            CardAttribute<?> listAttribute = new CardAttribute<>();
            cardAttribute = listAttribute;
        } else {
            throw new NotImplementedException("'" + type + "'");
        }
        cardAttribute.setId(row.getId());
        cardAttribute.setName(row.getName());
        cardAttribute.setData(row.getData());
        cardAttribute.getChildren().setAll(row.getChildren().stream()
                .map((Function<? super CardAttributeRow, ? extends CardAttribute<?>>)
                        this::fromRow).toList());
        return (S) cardAttribute;
    }

    private <S extends CardAttribute<?>> Iterable<CardAttributeRow> toRows(Iterable<S> entities) {
        List<CardAttributeRow> list = new ArrayList<>();
        for (S entity : entities) {
            list.add(toRow(entity));
        }
        return list;
    }

    <S extends CardAttribute<?>> Iterable<S> fromRows(Iterable<CardAttributeRow> rows) {
        List<S> list = new ArrayList<>();
        for (CardAttributeRow row : rows) {
            list.add(fromRow(row));
        }
        return list;
    }

}