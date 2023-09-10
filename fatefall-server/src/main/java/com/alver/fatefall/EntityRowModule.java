package com.alver.fatefall;

import com.alver.fatefall.data.entity.*;
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
