package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Template;
import javafx.scene.Parent;

public abstract class TemplateFX extends EntityFX implements Template {

	public abstract Parent build(CardFaceFX<?> cardFaceFX);

}

