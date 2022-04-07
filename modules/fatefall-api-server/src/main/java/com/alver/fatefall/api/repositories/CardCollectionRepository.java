package com.alver.fatefall.api.repositories;

import com.alver.fatefall.api.models.CardCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CardCollectionRepository extends JpaRepository<CardCollection, Long> {
}