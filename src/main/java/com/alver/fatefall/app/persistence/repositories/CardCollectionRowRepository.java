package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.app.persistence.models.CardCollectionRow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCollectionRowRepository extends CrudRepository<CardCollectionRow, String> {}