package com.other.fatefall;

import com.alver.fatefall.app.fx.components.FXMLAutoLoader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FxmlEditorConfiguration {

    @Bean
    public FXMLAutoLoader getFXMLAutoLoader() {
        return new FXMLAutoLoader();
    }
}