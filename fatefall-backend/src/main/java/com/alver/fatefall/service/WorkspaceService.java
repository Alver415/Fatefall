package com.alver.fatefall.service;

import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.data.entity.WorkspaceRow;
import com.alver.fatefall.data.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService extends EntityService<Workspace<?>, WorkspaceRow> {

	@Autowired
	public WorkspaceService(EntityRepository<WorkspaceRow> repository) {
		super(repository);
	}
}
