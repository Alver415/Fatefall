package com.alver.fatefall.fx.core.json;

import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.springframework.stereotype.Component;

@Component
public class JacksonFXModule extends SimpleModule {

    public JacksonFXModule(){
        super("JacksonFXModule");
        addDeserializer(SimpleObjectProperty.class, new SimpleObjectPropertyDeserializer());
        addDeserializer(SimpleListProperty.class, new SimpleListPropertyDeserializer());
        addDeserializer(SimpleMapProperty.class, new SimpleMapPropertyDeserializer());
        addDeserializer(TreeProperty.class, new TreePropertyDeserializer());

        addSerializer(new SimpleListPropertySerializer());
        addSerializer(new TreePropertySerializer());
    }
}
