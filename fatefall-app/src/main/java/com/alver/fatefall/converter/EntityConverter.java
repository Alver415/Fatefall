package com.alver.fatefall.converter;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.app.fx.entity.EntityFX;
import com.alver.fatefall.data.entity.Entity;
import com.alver.fatefall.data.entity.EntityRow;
import com.alver.fatefall.service.EntityService;

import java.util.List;
import java.util.Optional;


public abstract class EntityConverter<ENTITY extends Entity, FX extends EntityFX, ROW extends EntityRow> implements EntityApi<FX> {

    private final EntityService<ENTITY, ROW> service;

    public EntityConverter(EntityService<ENTITY, ROW> service) {
        this.service = service;
    }

    public abstract FX convert(ROW row);

    @Override
    public List<FX> getAll() {
        return service.getAll().stream().map(a -> convert((ROW) a)).toList();
    }

    @Override
    public Optional<FX> getById(Long id) {
        return service.getById(id).map(a -> convert((ROW) a));
    }

    @Override
    public FX create(FX entity) {
        return convert((ROW) service.create((ENTITY) entity));
    }

    @Override
    public FX update(Long id, FX entity) {
        return convert((ROW) service.update(id, (ENTITY) entity));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
