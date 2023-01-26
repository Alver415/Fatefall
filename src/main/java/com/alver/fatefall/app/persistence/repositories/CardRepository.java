package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {}