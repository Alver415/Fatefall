package com.alver.fatefall.service;

import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.data.entity.WorkspaceRow;
import com.alver.fatefall.data.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService extends EntityService<Workspace<?, ?, ?>, WorkspaceRow> {

    @Autowired
    public WorkspaceService(
            WorkspaceRepository workspaceRepository) {
        super(workspaceRepository);
    }

}
