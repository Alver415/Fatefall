package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Entity;
import com.alver.fatefall.service.EntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class EntityController<T extends Entity> {

	private final EntityService<T> service;

	public EntityController(EntityService<T> service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<T>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<T>> getById(
			@RequestParam Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@PostMapping()
	public ResponseEntity<T> create(
			@RequestBody T entity) {
		return ResponseEntity.ok(service.create(entity));
	}

	@PutMapping("/{id}")
	public ResponseEntity<T> update(
			@PathVariable Long id,
			@RequestBody T updated) {
		return ResponseEntity.ok(service.update(id, updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
