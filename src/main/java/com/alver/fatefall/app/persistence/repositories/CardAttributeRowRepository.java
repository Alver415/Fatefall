package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.app.persistence.models.CardAttributeRow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAttributeRowRepository extends CrudRepository<CardAttributeRow, String> {


}