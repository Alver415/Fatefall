package com.alver.fatefall.app.configuration;

import com.alver.scryfall.api.ScryfallApiClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("scryfall")
public class ScryfallConfiguration {

    protected String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Bean
    public ScryfallApiClient getScryfallClient() {
        return new ScryfallApiClient(url);
    }
}
