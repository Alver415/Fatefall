package com.alver.fatefall.server.service;

import com.alver.fatefall.core.entity.Template;
import com.alver.fatefall.server.repository.EntityRepository;
import com.alver.fatefall.server.model.TemplateRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TemplateService extends EntityService<Template, TemplateRow> {

	@Autowired
	public TemplateService(EntityRepository<TemplateRow> repository) {
		super(repository);
	}
}
