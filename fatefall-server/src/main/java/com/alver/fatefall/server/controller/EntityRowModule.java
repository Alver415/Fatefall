package com.alver.fatefall.server.controller;

import com.alver.fatefall.core.entity.Card;
import com.alver.fatefall.core.entity.CardFace;
import com.alver.fatefall.core.entity.Template;
import com.alver.fatefall.core.entity.Workspace;
import com.alver.fatefall.server.model.CardFaceRow;
import com.alver.fatefall.server.model.CardRow;
import com.alver.fatefall.server.model.TemplateRow;
import com.alver.fatefall.server.model.WorkspaceRow;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class EntityRowModule extends SimpleModule {

	public EntityRowModule() {
		super("EntityRowModule");
		addAbstractTypeMapping(Workspace.class, WorkspaceRow.class);
		addAbstractTypeMapping(Card.class, CardRow.class);
		addAbstractTypeMapping(CardFace.class, CardFaceRow.class);
		addAbstractTypeMapping(Template.class, TemplateRow.class);
	}

}
