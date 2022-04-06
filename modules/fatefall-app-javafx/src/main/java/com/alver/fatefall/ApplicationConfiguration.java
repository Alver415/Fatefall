package com.alver.fatefall;

import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.client.RemoteFatefallApi;
import com.alver.fatefall.api.local.LocalFatefallApi;
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
    public FatefallApi getFatefallApiClient() {
        boolean local = false;
        if (!local) {
            String baseUrl = String.join(":", host, Integer.toString(port));
            return new RemoteFatefallApi(baseUrl);
        } else {
            return new LocalFatefallApi();
        }
    }

    @Bean
    public ScryfallApiClient getScryfallClient() {
        String baseUrl = "https://api.scryfall.com";
        return new ScryfallApiClient(baseUrl);
    }
}
