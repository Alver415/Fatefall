package com.alver.fatefall;

import com.scryfall.api.ScryfallClient;
import com.scryfall.api.implementation.ScryfallClientImpl;
import mse.MseCliProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class FatefallConfiguration {

    /** Spring Bean Configuration */
    @Bean
    public ScryfallClient getScryfallClient() {
        return new ScryfallClientImpl();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MseCliProcess getMagicSetEditorProcess() throws IOException {
        return new MseCliProcess();
    }
}
