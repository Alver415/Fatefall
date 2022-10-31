package com.alver.fatefall.api.server.repositories;

import com.alver.fatefall.api.models.CardCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardCollectionRepository extends JpaRepository<CardCollection, Long> {
}