package com.alver.fatefall.server.repository;

import com.alver.fatefall.server.model.EntityRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends EntityRow> extends JpaRepository<T, Long> {
}