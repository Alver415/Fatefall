package com.alver.fatefall.api.repositories;

import com.alver.fatefall.api.base.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}