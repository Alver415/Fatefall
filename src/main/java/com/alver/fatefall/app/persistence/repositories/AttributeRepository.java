package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.api.models.attributes.BooleanAttribute;
import com.alver.fatefall.api.models.attributes.DoubleAttribute;
import com.alver.fatefall.api.models.attributes.IntegerAttribute;
import com.alver.fatefall.api.models.attributes.StringAttribute;
import com.alver.fatefall.app.persistence.models.AttributeRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class AttributeRepository extends AbstractRepository<Attribute<?>, AttributeRow, String> {

    @Autowired
    AttributeRepository(AttributeRowRepository wrappedRepository) {
        super(wrappedRepository);
    }

    @Override
    protected <S extends Attribute<?>> AttributeRow instantiateRow(S entity) {
        AttributeRow row = new AttributeRow();
        row.setType(entity.getClass().getName());
        return row;
    }

    @Override
    protected <S extends Attribute<?>> S instantiateEntity(AttributeRow row) {
        S attribute;
        String type = row.getType();
        if (Objects.equals(type, StringAttribute.class.getName())) {
            attribute = (S) new StringAttribute();
        } else if (Objects.equals(type, DoubleAttribute.class.getName())) {
            attribute = (S) new DoubleAttribute();
        } else if (Objects.equals(type, IntegerAttribute.class.getName())) {
            attribute = (S) new IntegerAttribute();
        } else if (Objects.equals(type, BooleanAttribute.class.getName())) {
            attribute = (S) new BooleanAttribute();
        } else {
            throw new RuntimeException("Unrecognized type: " + type);
        }
        return attribute;
    }
}