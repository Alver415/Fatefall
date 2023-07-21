package com.alver.fatefall.client.entity;

import com.alver.fatefall.data.entity.Field;
import com.alver.fatefall.data.entity.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WorkspaceClient extends EntityClient<Workspace> {

	@Autowired
	public WorkspaceClient(WebClient webClient) {
		super(webClient, "workspace");
	}
}
