package com.alver.fatefall.service;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.data.entity.Entity;
import com.alver.fatefall.data.repository.EntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EntityService<T extends Entity> implements EntityApi<T> {

	protected final EntityRepository<T> repository;

	@Autowired
	public EntityService(EntityRepository<T> repository) {
		this.repository = repository;
	}

	public List<T> getAll() {
		return repository.findAll();
	}

	public Optional<T> getById(Long id) {
		return repository.findById(id);
	}

	public T create(T entity) {
		validateIdDoesNotExist(entity.getId());
		return repository.save(entity);
	}

	public T update(Long id, T entity) {
		validateIdDoesExist(id);
		return repository.save(entity);
	}

	public void delete(Long id) {
		validateIdDoesExist(id);
		repository.deleteById(id);
	}

	private void validateIdDoesExist(Long id) {
		if (repository.findById(id).isEmpty()) {
			String message = "Not found: %s".formatted(id);
			throw new EntityNotFoundException(message);
		}
	}

	private void validateIdDoesNotExist(Long id) {
		if (id != null){
			String message = "Already exists: %s".formatted(id);
			throw new RuntimeException(message);
		}
	}
}
