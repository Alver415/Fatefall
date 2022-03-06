package com.alver.fatefall.api;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistedObject {

    @Id
    @GeneratedValue
    @Column(name = "pk", nullable = false)
    private Long pk;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

}
