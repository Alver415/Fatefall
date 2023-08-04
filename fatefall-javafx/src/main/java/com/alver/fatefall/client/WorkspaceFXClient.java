package com.alver.fatefall.client;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WorkspaceFXClient extends EntityClient<WorkspaceFX> {

	private static final ParameterizedTypeReference<WorkspaceFX> TYPE = new ParameterizedTypeReference<>() {};
	private static final ParameterizedTypeReference<List<WorkspaceFX>> LIST_TYPE = new ParameterizedTypeReference<>() {};

	@Autowired
	public WorkspaceFXClient(WebClient webClient) {
		super(webClient, "workspace", TYPE, LIST_TYPE);
	}
}
