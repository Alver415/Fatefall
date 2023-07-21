package com.alver.fatefall.app;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.data.entity.Workspace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkspaceConfiguration {

	@Autowired
	public EntityApi<Workspace> workspaceApi;

	@Bean
	public ObservableList<Workspace> getWorkspaces() {
		return FXCollections.observableArrayList(workspaceApi.getAll());
	}
}
