package com.alver.fatefall.server.service;

import com.alver.fatefall.core.entity.Workspace;
import com.alver.fatefall.server.repository.WorkspaceRepository;
import com.alver.fatefall.server.model.WorkspaceRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService extends EntityService<Workspace, WorkspaceRow> {

    @Autowired
    public WorkspaceService(
            WorkspaceRepository workspaceRepository) {
        super(workspaceRepository);
    }

}
