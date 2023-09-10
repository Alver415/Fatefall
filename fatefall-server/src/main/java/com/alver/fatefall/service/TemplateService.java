package com.alver.fatefall.service;

import com.alver.fatefall.data.entity.Template;
import com.alver.fatefall.data.entity.TemplateRow;
import com.alver.fatefall.data.repository.EntityRepository;
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
