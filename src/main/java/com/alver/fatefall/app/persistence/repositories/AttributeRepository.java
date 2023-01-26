package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends CrudRepository<Attribute, String> {


}