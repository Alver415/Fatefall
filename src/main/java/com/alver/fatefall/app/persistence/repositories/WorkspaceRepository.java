package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Workspace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends CrudRepository<Workspace, String> {}