package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Template;
import com.alver.fatefall.data.entity.TemplateRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class TemplateController extends EntityController<Template, TemplateRow> {

	@Autowired
	public TemplateController(EntityService<Template, TemplateRow> service) {
		super(service);
	}

}
