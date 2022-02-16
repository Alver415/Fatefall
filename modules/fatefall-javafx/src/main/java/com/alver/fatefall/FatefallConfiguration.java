package com.alver.fatefall;

import com.alver.fatefall.repositories.DatabaseManager;
import com.alver.fatefall.repositories.ImageRepository;
import com.scryfall.api.ScryfallClient;
import com.scryfall.api.implementation.ScryfallClientImpl;
import mse.MagicSetEditorProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
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
    public MagicSetEditorProcess getMagicSetEditorProcess() throws IOException {
        return new MagicSetEditorProcess();
    }
}
