package com.alver.fatefall.server.controller;

import com.alver.fatefall.core.api.RequestMappings;
import com.alver.fatefall.core.entity.Workspace;
import com.alver.fatefall.server.model.WorkspaceRow;
import com.alver.fatefall.server.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappings.WORKSPACES)
public class WorkspaceRestController extends EntityRestController<Workspace, WorkspaceRow> {

	@Autowired
	public WorkspaceRestController(EntityService<Workspace, WorkspaceRow> service) {
		super(service);
	}

}
