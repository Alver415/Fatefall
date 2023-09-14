package com.alver.fatefall.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class CardFaceRow extends EntityRow implements CardFace<TemplateRow> {

    @ManyToOne(optional = false)
    protected CardRow card;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected TemplateRow template;

    @Override
    @JsonIgnore
    public CardRow getCard() {
        return card;
    }

    @Override
    public TemplateRow getTemplate() {
        return template;
    }
    public void setTemplate(TemplateRow template) {
        this.template = template;
    }
}
