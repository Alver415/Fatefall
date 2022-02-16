package com.alver.fatefall.repositories;

import com.scryfall.api.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class EntityRepository<T> {

    protected Class<T> entityType;

    @Autowired
    protected DatabaseManager databaseManager;

    protected EntityRepository(Class<T> entityType){
        this.entityType = entityType;
    }

    public Card get(UUID id) {
        return databaseManager.inSession((s) -> {
            return s.get(Card.class, id);
        });
    }
    public T save(T entity) {
        return databaseManager.inSession(session -> (T) session.merge(entity));
    }

    public List<T> saveAll(List<T> entities) {
        return databaseManager.inSession(session -> {
            return entities.stream().map(e -> (T)session.merge(e)).toList();
        });
    }

    public void delete(T entity) {
        databaseManager.inSession(session -> {
            session.delete(entity);
        });
    }

    public List<T> getAll() {
        return databaseManager.inSession(session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(entityType);
            criteria.from(entityType);
            return session.createQuery(criteria).getResultList();
        });
    }

}
