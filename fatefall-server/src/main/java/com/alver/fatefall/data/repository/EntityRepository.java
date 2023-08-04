package com.alver.fatefall.data.repository;

import com.alver.fatefall.data.entity.Entity;
import com.alver.fatefall.data.entity.EntityRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends EntityRow> extends JpaRepository<T, Long> {
}