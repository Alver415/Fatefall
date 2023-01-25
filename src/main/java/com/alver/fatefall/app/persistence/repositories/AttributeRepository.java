package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.app.persistence.models.AttributeRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeRepository extends AbstractRepository<Attribute, AttributeRow, String> {

    @Autowired
    AttributeRepository(AttributeRowRepository wrappedRepository) {
        super(wrappedRepository);
    }

    @Override
    protected <S extends Attribute> AttributeRow instantiateRow(S entity) {
        return new AttributeRow();
    }

    @Override
    protected <S extends Attribute> S instantiateEntity(AttributeRow row) {
        return (S) new Attribute();
    }
}