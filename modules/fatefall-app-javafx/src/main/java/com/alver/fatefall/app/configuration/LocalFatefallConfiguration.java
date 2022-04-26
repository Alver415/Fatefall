package com.alver.fatefall.app.configuration;

import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.client.RemoteFatefallApi;
import com.alver.fatefall.api.server.local.LocalFatefallApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(Profiles.LOCAL)
@ConfigurationProperties("fatefall")
@ComponentScan("com.alver.fatefall.api.server")
public class LocalFatefallConfiguration {

    @Bean
    @Profile(Profiles.LOCAL)
    public FatefallApi getLocalFatefallApi() {
        return new LocalFatefallApi();
    }
}
