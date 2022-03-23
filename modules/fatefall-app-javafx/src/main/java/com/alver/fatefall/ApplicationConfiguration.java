package com.alver.fatefall;

import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.scryfall.api.ScryfallApiClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("fatefall.api.server")
public class ApplicationConfiguration {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    @Bean
    public FatefallApiClient getFatefallApiClient() {
        String baseUrl = String.join(":", host, Integer.toString(port));
        return new FatefallApiClient(baseUrl);
    }

    @Bean
    public ScryfallApiClient getScryfallClient() {
        String baseUrl = "https://api.scryfall.com";
        return new ScryfallApiClient(baseUrl);
    }
}
