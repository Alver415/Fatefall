package com.alver.fatefall.api.entity;

import com.alver.fatefall.data.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityApi<T extends Entity> {

	List<T> getAll();

	Optional<T> getById(Long id);

	T create(T entity);

	T update(Long id, T entity);

	void delete(Long id);
}
