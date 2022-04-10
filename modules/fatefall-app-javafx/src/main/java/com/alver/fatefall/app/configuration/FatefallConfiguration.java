package com.alver.fatefall.app.configuration;

import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.client.RemoteFatefallApi;
import com.alver.fatefall.api.server.local.LocalFatefallApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("fatefall")
public class FatefallConfiguration {

    protected String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Bean
    @Profile(Profiles.REMOTE)
    public FatefallApi getRemoteFatefallApi() {
        return new RemoteFatefallApi(url);
    }

    @Bean
    @Profile(Profiles.LOCAL)
    public FatefallApi getLocalFatefallApi() {
        return new LocalFatefallApi();
    }
}
