package com.alver.fatefall.app.configuration;

import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.server.local.FatefallApiServer;
import org.hsqldb.server.Server;
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
    public FatefallApi getLocalFatefallApi() {
        return new FatefallApiServer();
    }

    @Bean("database")
    public Server getDatabase(){
        Server server = new Server();
        server.setDatabaseName(0, "fatefall");
        server.setDatabasePath(0, "file:database/fatefall");
        server.setPort(9001); // this is the default port
        server.start();
        return server;
    }

}
