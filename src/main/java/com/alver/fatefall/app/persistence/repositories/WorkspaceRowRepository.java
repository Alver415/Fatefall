package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.app.persistence.models.WorkspaceRow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRowRepository extends CrudRepository<WorkspaceRow, String> {}