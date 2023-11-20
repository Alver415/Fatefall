package com.alver.fatefall.server.controller;

import com.alver.fatefall.core.api.RequestMappings;
import com.alver.fatefall.core.entity.Template;
import com.alver.fatefall.server.model.TemplateRow;
import com.alver.fatefall.server.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappings.TEMPLATES)
public class TemplateRestController extends EntityRestController<Template, TemplateRow> {

	@Autowired
	public TemplateRestController(EntityService<Template, TemplateRow> service) {
		super(service);
	}

}
