package com.alver.fatefall.api.repositories;

import com.alver.fatefall.api.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CardRepository extends JpaRepository<Card, Long> {
}