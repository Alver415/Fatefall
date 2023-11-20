package com.alver.fatefall.server.model;

import com.alver.fatefall.core.entity.Template;
import jakarta.persistence.Entity;

@Entity
public class TemplateRow extends EntityRow implements Template {

    protected String imageUrl;
    protected String fxmlUrl;

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String getFxmlUrl() {
        return fxmlUrl;
    }

    public void setFxmlUrl(String fxmlUrl) {
        this.fxmlUrl = fxmlUrl;
    }
}

