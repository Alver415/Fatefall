package com.alver.fatefall;

import com.alver.fatefall.database.DatabaseManager;
import com.alver.fatefall.database.ImageRepository;
import com.scryfall.api.ScryfallClient;
import com.scryfall.api.implementation.ScryfallClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FatefallConfiguration {

    /** Spring Bean Configuration */
    @Bean
    public ScryfallClient getScryfallClient() {
        return new ScryfallClientImpl();
    }
    @Bean
    public ImageRepository getImageRepository() {
        return new ImageRepository();
    }
    @Bean
    public DatabaseManager getDatabaseManager() {
        return new DatabaseManager();
    }
}
