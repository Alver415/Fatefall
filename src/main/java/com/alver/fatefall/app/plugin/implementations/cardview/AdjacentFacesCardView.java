package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@FXMLAutoLoad
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdjacentFacesCardView extends AbstractCardView<AdjacentFacesCardView>{

}
