package com.alver.fatefall.app.persistence.models;

import com.alver.fatefall.api.models.Attribute;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "attribute")
public class AttributeRow extends AbstractRow<Attribute<?>> {

    protected String type;
    @Lob
    protected String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
