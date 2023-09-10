package com.alver.fatefall.json;

import com.alver.fatefall.app.fx.model.SimpleTreeProperty;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.springframework.stereotype.Component;

@Component
public class JacksonFXModule extends SimpleModule {

    public JacksonFXModule(){
        super("JacksonFXModule");
        addDeserializer(SimpleListProperty.class, new SimpleListPropertyDeserializer());
        addDeserializer(SimpleObjectProperty.class, new SimpleObjectPropertyDeserializer());
        addDeserializer(SimpleMapProperty.class, new SimpleMapPropertyDeserializer());
        addDeserializer(SimpleTreeProperty.class, new SimpleTreePropertyDeserializer());
    }
}
