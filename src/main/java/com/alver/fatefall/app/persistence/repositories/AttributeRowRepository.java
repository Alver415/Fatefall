package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.app.persistence.models.AttributeRow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRowRepository extends CrudRepository<AttributeRow, String> {


}