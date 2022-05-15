package com.alver.fatefall.api;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistedObject {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getPk() {
        return id;
    }

    public void setPk(Long id) {
        this.id = id;
    }

}
