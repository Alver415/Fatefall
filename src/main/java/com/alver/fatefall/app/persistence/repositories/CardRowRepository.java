package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.app.persistence.models.CardRow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRowRepository extends CrudRepository<CardRow, String> {}