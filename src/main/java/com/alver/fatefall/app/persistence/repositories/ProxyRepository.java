package com.alver.fatefall.app.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class ProxyRepository<T, R, ID> implements CrudRepository<T, ID> {

	protected CrudRepository<R, ID> repository;

	ProxyRepository(CrudRepository<R, ID> repository){
		this.repository = repository;
	}

	@Override
	public <S extends T> S save(S entity) {
		return fromRow(repository.save(toRow(entity)));
	}
	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
		return fromRows(repository.saveAll(toRows(entities)));
	}
	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id).map(this::fromRow);
	}
	@Override
	public boolean existsById(ID id) {
		return repository.existsById(id);
	}
	@Override
	public Iterable<T> findAll() {
		return fromRows(repository.findAll());
	}
	@Override
	public Iterable<T> findAllById(Iterable<ID> strings) {
		return fromRows(repository.findAllById(strings));
	}
	@Override
	public long count() {
		return repository.count();
	}
	@Override
	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	@Override
	public void delete(T entity) {
		repository.delete(toRow(entity));
	}
	@Override
	public void deleteAllById(Iterable<? extends ID> strings) {
		repository.deleteAllById(strings);
	}
	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		repository.deleteAll(toRows(entities));
	}
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	abstract <S extends T> R toRow(S attribute);

	abstract <S extends T> S fromRow(R row);
	abstract <S extends T> Iterable<R> toRows(Iterable<S> entities);
	abstract <S extends T> Iterable<S> fromRows(Iterable<R> rows);
}