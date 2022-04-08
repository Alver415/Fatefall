package com.alver.fatefall.app;

import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.client.RemoteFatefallApi;
import com.alver.fatefall.api.server.local.LocalFatefallApi;
import com.alver.scryfall.api.ScryfallApiClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("fatefall.api.server")
public class ApplicationConfiguration {

    private interface SpringProfile {
        String LOCAL = "local";
        String REMOTE = "remote";
        String HOST_DESKTOP = "desktop";
        String HOST_LAPTOP = "laptop";
    }

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
    @Profile(SpringProfile.REMOTE)
    public FatefallApi getRemoteFatefallApi() {
        String baseUrl = String.join(":", host, Integer.toString(port));
        return new RemoteFatefallApi(baseUrl);
    }

    @Bean
    @Profile(SpringProfile.LOCAL)
    public FatefallApi getLocalFatefallApi() {
        return new LocalFatefallApi();
    }

    @Bean
    public ScryfallApiClient getScryfallClient() {
        String baseUrl = "https://api.scryfall.com";
        return new ScryfallApiClient(baseUrl);
    }
}
