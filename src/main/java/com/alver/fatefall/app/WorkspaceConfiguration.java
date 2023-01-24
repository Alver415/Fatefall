package com.alver.fatefall.app;

import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.persistence.repositories.WorkspaceRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class WorkspaceConfiguration {

	@Autowired
	public WorkspaceRepository workspaceRepository;

	@Bean
	@DependsOn("hsqlServer")
	public ObservableList<Workspace> getWorkspaces() {
		ObservableList<Workspace> list = FXCollections.observableArrayList();
		workspaceRepository.findAll().forEach(list::add);
		return list;
	}
}
