package com.alver.fatefall.app;

import com.alver.fatefall.api.entity.WorkspaceApi;
import com.alver.fatefall.data.entity.Workspace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class WorkspaceConfiguration {

	@Autowired
	public WorkspaceApi workspaceApi;

	@Bean
	@DependsOn("hsqlServer")
	public ObservableList<Workspace> getWorkspaces() {
		return FXCollections.observableList(workspaceApi.getAll());
	}
}
