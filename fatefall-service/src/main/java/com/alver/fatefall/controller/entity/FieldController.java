package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Entity;
import com.alver.fatefall.data.entity.Field;
import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.service.EntityService;
import com.alver.fatefall.service.FieldService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fields")
public class FieldController extends EntityController<Field> {

	@Autowired
	public FieldController(EntityService<Field> service) {
		super(service);
	}

}
