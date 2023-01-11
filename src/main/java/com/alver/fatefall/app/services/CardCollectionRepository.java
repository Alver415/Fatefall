package com.alver.fatefall.app.services;

import com.alver.fatefall.api.models.CardCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCollectionRepository extends CrudRepository<CardCollection, Long> {}