package com.alver.fatefall.controller;

import com.alver.fatefall.data.entity.Field;
import com.alver.fatefall.service.FieldService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fields")
public class FieldController {

	private final FieldService fieldService;

	@Autowired
	public FieldController(FieldService fieldService) {
		this.fieldService = fieldService;
	}

	@GetMapping
	public ResponseEntity<List<Field>> getAllFields() {
		return ResponseEntity.ok(fieldService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Field> getFieldById(
			@RequestParam Long id) {
		return ResponseEntity.ok(fieldService.findById(id)
				.orElseThrow(EntityNotFoundException::new));
	}

	@PostMapping()
	public ResponseEntity<Field> createField(
			@RequestBody Field field) {
		return ResponseEntity.ok(fieldService.save(field));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Field> updateField(
			@PathVariable Long id,
			@RequestBody Field fieldWithUpdates) {
		Optional<Field> existing = fieldService.findById(id);
		if (existing.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Field saved = fieldService.save(fieldWithUpdates);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteField(
			@PathVariable Long id) {
		Optional<Field> existing = fieldService.findById(id);
		if (existing.isPresent()) {
			fieldService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
