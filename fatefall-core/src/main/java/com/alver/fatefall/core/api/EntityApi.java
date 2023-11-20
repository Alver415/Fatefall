package com.alver.fatefall.core.api;

import com.alver.fatefall.core.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityApi<T extends Entity> {

	List<T> getAll();

	Optional<T> getById(Long id);

	T create(T entity);

	T update(Long id, T entity);

	void delete(Long id);
}
