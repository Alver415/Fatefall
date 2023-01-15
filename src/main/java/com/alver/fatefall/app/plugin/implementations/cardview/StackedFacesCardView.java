package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import javafx.fxml.FXML;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@FXMLAutoLoad
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StackedFacesCardView extends AbstractCardView<StackedFacesCardView> {

    @FXML
    private void initialize() {
        List.of(frontFace, backFace).forEach(face -> face.setOnMouseEntered(e -> face.toFront()));
    }
}
