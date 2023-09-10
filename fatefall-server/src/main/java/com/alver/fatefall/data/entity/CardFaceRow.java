package com.alver.fatefall.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;

@Entity
public class CardFaceRow extends EntityRow implements CardFace<TemplateRow> {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TemplateRow template;

    @Override
    public TemplateRow getTemplate() {
        return template;
    }

    public void setTemplate(TemplateRow template) {
        this.template = template;
    }
}
