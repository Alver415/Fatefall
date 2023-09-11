package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Entity;
import com.alver.fatefall.data.entity.EntityRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<Optional<E>> getById(
			@RequestParam Long id) {
		return ResponseEntity.ok(service.getById(id));
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
