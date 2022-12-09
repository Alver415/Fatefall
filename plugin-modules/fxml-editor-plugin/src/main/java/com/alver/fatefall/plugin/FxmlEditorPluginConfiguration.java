package com.alver.fatefall.plugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FxmlEditorPluginConfiguration {

    @Bean
    public MessageProvider messageProvider() {
        return new HelloMessageProvider();
    }

}