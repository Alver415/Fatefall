package com.alver.fatefall.data.repository;

import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.data.entity.WorkspaceRow;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends EntityRepository<WorkspaceRow> {
}