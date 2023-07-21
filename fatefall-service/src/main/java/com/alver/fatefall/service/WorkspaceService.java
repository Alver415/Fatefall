package com.alver.fatefall.service;

import com.alver.fatefall.data.repository.EntityRepository;
import com.alver.fatefall.data.repository.WorkspaceRepository;
import com.alver.fatefall.data.entity.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService extends EntityService<Workspace> {

	@Autowired
	public WorkspaceService(EntityRepository<Workspace> repository) {
		super(repository);
	}
}
