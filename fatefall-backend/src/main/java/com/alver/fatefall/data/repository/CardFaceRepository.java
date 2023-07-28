package com.alver.fatefall.data.repository;

import com.alver.fatefall.data.entity.CardFace;
import com.alver.fatefall.data.entity.CardFaceRow;
import org.springframework.stereotype.Repository;

@Repository
public interface CardFaceRepository extends EntityRepository<CardFaceRow> {
}