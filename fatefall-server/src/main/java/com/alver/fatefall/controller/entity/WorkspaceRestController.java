package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.data.entity.WorkspaceRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workspace")
public class WorkspaceRestController extends EntityRestController<Workspace<?, ?, ?>, WorkspaceRow> {

	@Autowired
	public WorkspaceRestController(EntityService<Workspace<?, ?, ?>, WorkspaceRow> service) {
		super(service);
	}

}
