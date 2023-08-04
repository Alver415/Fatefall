package com.alver.fatefall.service;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.data.entity.Entity;
import com.alver.fatefall.data.entity.EntityRow;
import com.alver.fatefall.data.repository.EntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class EntityService<E extends Entity, R extends EntityRow> implements EntityApi<E> {

	protected final EntityRepository<R> repository;

	@Autowired
	public EntityService(EntityRepository<R> repository) {
		this.repository = repository;
	}

	public List<E> getAll() {
		return (List<E>) repository.findAll();
	}

	public Optional<E> getById(Long id) {
		return (Optional<E>) repository.findById(id);
	}

	public E create(E entity) {
		if (entity.getId() != null){
			String message = "Already exists: %s".formatted(entity.getId());
			throw new RuntimeException(message);
		}
		return (E)repository.save((R)entity);
	}

	public E update(Long id, E entity) {
		if (!Objects.equals(id, entity.getId())){
			String message = "Ids don't match: %s and %s".formatted(id, entity.getId());
			throw new RuntimeException(message);
		}
		if (repository.findById(id).isEmpty()) {
			String message = "Not found: %s".formatted(id);
			throw new EntityNotFoundException(message);
		}
		return (E)repository.save((R)entity);
	}

	public void delete(Long id) {
		if (repository.findById(id).isEmpty()) {
			String message = "Not found: %s".formatted(id);
			throw new EntityNotFoundException(message);
		}
		repository.deleteById(id);
	}

}
