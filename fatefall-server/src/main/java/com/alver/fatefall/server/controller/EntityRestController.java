package com.alver.fatefall.server.controller;

import com.alver.fatefall.core.entity.Entity;
import com.alver.fatefall.server.model.EntityRow;
import com.alver.fatefall.server.service.EntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class EntityRestController<E extends Entity, R extends EntityRow & Entity> {

	private final EntityService<E, R> service;

	public EntityRestController(EntityService<E, R> service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<E>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<E> getById(
			@PathVariable Long id) {
		return ResponseEntity.of(service.getById(id));
	}

	@PostMapping()
	public ResponseEntity<E> create(
			@RequestBody E entity) {
		return ResponseEntity.ok(service.create(entity));
	}

	@PutMapping("/{id}")
	public ResponseEntity<E> update(
			@PathVariable Long id,
			@RequestBody E updated) {
		return ResponseEntity.ok(service.update(id, updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
