package com.alver.fatefall.fx.app.rest;

import com.alver.fatefall.client.rest.WorkspaceClient;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WorkspaceFXClient extends WorkspaceClient<WorkspaceFX> {

	private static final ParameterizedTypeReference<WorkspaceFX> TYPE = new ParameterizedTypeReference<>() {
	};
	private static final ParameterizedTypeReference<List<WorkspaceFX>> LIST_TYPE = new ParameterizedTypeReference<>() {
	};

	@Autowired
	public WorkspaceFXClient(WebClient webClient) {
		super(webClient, TYPE, LIST_TYPE);
	}
}
