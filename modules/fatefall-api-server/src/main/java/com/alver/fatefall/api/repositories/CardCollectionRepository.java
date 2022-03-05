package com.alver.fatefall.api.repositories;

import com.alver.scryfall.api.models.CardCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCollectionRepository extends JpaRepository<CardCollection, Long> {
}