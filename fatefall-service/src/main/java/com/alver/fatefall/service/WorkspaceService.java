package com.alver.fatefall.service;

import com.alver.fatefall.data.repository.WorkspaceRepository;
import com.alver.fatefall.data.entities.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {

	private final WorkspaceRepository workspaceRepository;

	@Autowired
	public WorkspaceService(WorkspaceRepository workspaceRepository) {
		this.workspaceRepository = workspaceRepository;
	}

	public List<Workspace> findAll() {
		return workspaceRepository.findAll();
	}

	public Optional<Workspace> findById(Long id) {
		return workspaceRepository.findById(id);
	}

	public Workspace save(Workspace workspace) {
		return workspaceRepository.save(workspace);
	}

	public void deleteById(Long id) {
		workspaceRepository.deleteById(id);
	}
}
