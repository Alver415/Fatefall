package com.alver.fatefall.client.rest;

import com.alver.fatefall.core.api.RequestMappings;
import com.alver.fatefall.core.api.WorkspacesApi;
import com.alver.fatefall.core.entity.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class WorkspaceClient<T extends Workspace> extends EntityClient<T> implements WorkspacesApi<T> {

	@Autowired
	public WorkspaceClient(
			WebClient webClient,
			ParameterizedTypeReference<T> type,
			ParameterizedTypeReference<List<T>> listType) {
		super(webClient, RequestMappings.WORKSPACES, type, listType);
	}
}
