package com.alver.fatefall.client.entity;

import com.alver.fatefall.data.entity.Field;
import com.alver.fatefall.data.entity.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WorkspaceClient extends EntityClient<Workspace> {

	private static final ParameterizedTypeReference<Workspace> TYPE = new ParameterizedTypeReference<>() {};
	private static final ParameterizedTypeReference<List<Workspace>> LIST_TYPE = new ParameterizedTypeReference<>() {};

	@Autowired
	public WorkspaceClient(WebClient webClient) {
		super(webClient, "workspace", TYPE, LIST_TYPE);
	}
}
