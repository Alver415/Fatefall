package com.alver.fatefall.api.server.repositories.jpa;

import com.alver.fatefall.api.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}