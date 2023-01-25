package com.alver.fatefall.app.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "attribute")
public class AttributeRow extends AbstractRow {

    @Lob
    protected String value;
    protected Double topValue;
    protected Double rightValue;
    protected Double bottomValue;
    protected Double leftValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getTopValue() {
        return topValue;
    }

    public void setTopValue(Double topValue) {
        this.topValue = topValue;
    }

    public Double getRightValue() {
        return rightValue;
    }

    public void setRightValue(Double rightValue) {
        this.rightValue = rightValue;
    }

    public Double getBottomValue() {
        return bottomValue;
    }

    public void setBottomValue(Double bottomValue) {
        this.bottomValue = bottomValue;
    }

    public Double getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(Double leftValue) {
        this.leftValue = leftValue;
    }
}
