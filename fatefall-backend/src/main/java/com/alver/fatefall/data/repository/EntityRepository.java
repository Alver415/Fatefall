package com.alver.fatefall.data.repository;

import com.alver.fatefall.data.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends Entity> extends JpaRepository<T, Long> {
}