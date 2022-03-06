package com.alver.fatefall.api;

import mse.MseCliProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class FatefallConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MseCliProcess getMagicSetEditorProcess() throws IOException {
        return new MseCliProcess();
    }
}
