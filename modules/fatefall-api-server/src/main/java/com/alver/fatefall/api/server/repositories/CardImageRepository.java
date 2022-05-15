package com.alver.fatefall.api.server.repositories;

import com.alver.fatefall.api.models.CardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardImageRepository extends JpaRepository<CardImage, Long> {
}