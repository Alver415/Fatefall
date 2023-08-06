package com.alver.fatefall.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.springframework.stereotype.Component;

@Component
public class JacksonFXModule extends SimpleModule {

    public JacksonFXModule(){
        super("JacksonFXModule");
        addDeserializer(SimpleListProperty.class, new SimpleListPropertyDeserializer());
        addDeserializer(SimpleObjectProperty.class, new SimpleObjectPropertyDeserializer());
    }
}
