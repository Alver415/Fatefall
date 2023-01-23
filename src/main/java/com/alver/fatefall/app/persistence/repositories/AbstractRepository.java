package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.AbstractEntity;
import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.api.models.attributes.BooleanAttribute;
import com.alver.fatefall.api.models.attributes.DoubleAttribute;
import com.alver.fatefall.api.models.attributes.IntegerAttribute;
import com.alver.fatefall.api.models.attributes.StringAttribute;
import com.alver.fatefall.app.persistence.models.AbstractRow;
import com.alver.fatefall.app.persistence.models.AttributeRow;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractRepository<T extends AbstractEntity, R extends AbstractRow<T>, ID> implements CrudRepository<T, ID> {

	protected CrudRepository<R, ID> wrappedRepository;

	AbstractRepository(CrudRepository<R, ID> wrappedRepository){
		this.wrappedRepository = wrappedRepository;
	}

	@Override
	public <S extends T> S save(S entity) {
		R row = createRow(entity);
		R saved = wrappedRepository.save(row);
		return createEntity(saved);
	}
	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
		return createEntities(wrappedRepository.saveAll(createRows(entities)));
	}
	@Override
	public Optional<T> findById(ID id) {
		return wrappedRepository.findById(id).map(this::createEntity);
	}
	@Override
	public boolean existsById(ID id) {
		return wrappedRepository.existsById(id);
	}
	@Override
	public Iterable<T> findAll() {
		return createEntities(wrappedRepository.findAll());
	}
	@Override
	public Iterable<T> findAllById(Iterable<ID> strings) {
		return createEntities(wrappedRepository.findAllById(strings));
	}
	@Override
	public long count() {
		return wrappedRepository.count();
	}
	@Override
	public void deleteById(ID id) {
		wrappedRepository.deleteById(id);
	}
	@Override
	public void delete(T entity) {
		wrappedRepository.delete(createRow(entity));
	}
	@Override
	public void deleteAllById(Iterable<? extends ID> strings) {
		wrappedRepository.deleteAllById(strings);
	}
	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		wrappedRepository.deleteAll(createRows(entities));
	}
	@Override
	public void deleteAll() {
		wrappedRepository.deleteAll();
	}


	protected <S extends T> S createEntity(R saved) {
		return hydrateEntity(saved, instantiateEntity(saved));
	}

	protected <S extends T> R createRow(S entity) {
		return hydrateRow(entity, instantiateRow(entity));
	}


	protected abstract <S extends T> R instantiateRow(S entity);
	protected <S extends T> R hydrateRow(S entity, R row){
		row.setId(entity.getId());
		row.setName(entity.getName());
		for (Attribute<?> attribute : entity.getAttributes()){
			row.getAttributes().add(toAttributeRow(attribute));
		}
		return row;
	}

	protected abstract <S extends T> S instantiateEntity(R row);
	protected <S extends T> S hydrateEntity(R row, S entity){
		entity.setId(row.getId());
		entity.setName(row.getName());
		for (AttributeRow attributeRow : row.getAttributes()){
			entity.addAttribute(toAttributeEntity(attributeRow));
		}
		return (S) entity;
	}

	protected <S extends T> Iterable<R> createRows(Iterable<S> entities){
		List<R> list = new ArrayList<>();
		for (S entity : entities){
			list.add(createRow(entity));
		}
		return list;
	}
	protected <S extends T> Iterable<S> createEntities(Iterable<R> rows){
		List<S> list = new ArrayList<>();
		for (R row : rows){
			list.add(createEntity(row));
		}
		return list;
	}

	protected <S extends Attribute<?>> AttributeRow toAttributeRow(S entity) {
		AttributeRow row = new AttributeRow();
		row.setId(entity.getId());
		row.setName(entity.getName());
		row.setType(entity.getClass().getName());
		row.setValue(String.valueOf(entity.getValue()));
		for (Attribute<?> attribute : entity.getAttributes()){
			row.getAttributes().add(toAttributeRow(attribute));
		}
		return row;
	}

	protected <S extends Attribute<?>> S toAttributeEntity(AttributeRow row) {
		Attribute<?> attribute;
		String type = row.getType();
		if (Objects.equals(type, StringAttribute.class.getName())) {
			StringAttribute stringAttribute = new StringAttribute();
			stringAttribute.setValue(row.getValue());
			attribute = stringAttribute;
		} else if (Objects.equals(type, DoubleAttribute.class.getName())) {
			DoubleAttribute doubleAttribute = new DoubleAttribute();
			doubleAttribute.setValue(Double.parseDouble(row.getValue()));
			attribute = doubleAttribute;
		} else if (Objects.equals(type, IntegerAttribute.class.getName())) {
			IntegerAttribute integerAttribute = new IntegerAttribute();
			integerAttribute.setValue(Integer.parseInt(row.getValue()));
			attribute = integerAttribute;
		} else if (Objects.equals(type, BooleanAttribute.class.getName())) {
			BooleanAttribute booleanAttribute = new BooleanAttribute();
			booleanAttribute.setValue(Boolean.parseBoolean(row.getValue()));
			attribute = booleanAttribute;
		} else {
			throw new RuntimeException("Unrecognized type: " + type);
		}

		attribute.setId(row.getId());
		attribute.setName(row.getName());
		for (AttributeRow child : row.getAttributes()){
			attribute.addAttribute(toAttributeEntity(child));
		}
		return (S) attribute;
	}
}