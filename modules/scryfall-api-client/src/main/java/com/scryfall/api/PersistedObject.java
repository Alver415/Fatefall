package com.scryfall.api;

import javax.persistence.*;

@MappedSuperclass
public class PersistedObject {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistedObject that = (PersistedObject) o;

        return pk != null ? pk.equals(that.pk) : that.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }
}
