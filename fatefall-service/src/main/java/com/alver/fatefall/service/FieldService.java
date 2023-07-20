package com.alver.fatefall.service;

import com.alver.fatefall.data.repository.FieldRepository;
import com.alver.fatefall.data.entity.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldService {

	private final FieldRepository fieldRepository;

	@Autowired
	public FieldService(FieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}

	public List<Field> findAll() {
		return fieldRepository.findAll();
	}

	public Optional<Field> findById(Long id) {
		return fieldRepository.findById(id);
	}

	public Field save(Field field) {
		return fieldRepository.save(field);
	}

	public void deleteById(Long id) {
		fieldRepository.deleteById(id);
	}
}
