package com.alver.fatefall.controller;

import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.service.WorkspaceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

	private final WorkspaceService workspaceService;

	@Autowired
	public WorkspaceController(WorkspaceService workspaceService) {
		this.workspaceService = workspaceService;
	}

	@GetMapping
	public ResponseEntity<List<Workspace>> getAllWorkspaces() {
		return ResponseEntity.ok(workspaceService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Workspace> getWorkspaceById(
			@RequestParam Long id) {
		return ResponseEntity.ok(workspaceService.findById(id)
				.orElseThrow(EntityNotFoundException::new));
	}

	@PostMapping()
	public ResponseEntity<Workspace> createWorkspace(
			@RequestBody Workspace workspace) {
		return ResponseEntity.ok(workspaceService.save(workspace));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Workspace> updateWorkspace(
			@PathVariable Long id,
			@RequestBody Workspace updated) {
		Optional<Workspace> existing = workspaceService.findById(id);
		if (!existing.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Workspace saved = workspaceService.save(updated);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWorkspace(
			@PathVariable Long id) {
		Optional<Workspace> existing = workspaceService.findById(id);
		if (!existing.isEmpty()) {
			workspaceService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
