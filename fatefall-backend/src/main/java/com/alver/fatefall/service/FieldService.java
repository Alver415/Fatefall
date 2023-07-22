package com.alver.fatefall.service;

import com.alver.fatefall.api.entity.FieldApi;
import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.repository.EntityRepository;
import com.alver.fatefall.data.repository.FieldRepository;
import com.alver.fatefall.data.entity.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldService extends EntityService<Field> {

	@Autowired
	public FieldService(EntityRepository<Field> repository) {
		super(repository);
	}
}
