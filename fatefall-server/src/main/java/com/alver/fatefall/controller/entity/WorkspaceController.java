package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Field;
import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.service.EntityService;
import com.alver.fatefall.service.FieldService;
import com.alver.fatefall.service.WorkspaceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController extends EntityController<Workspace> {

	@Autowired
	public WorkspaceController(EntityService<Workspace> service) {
		super(service);
	}

}
