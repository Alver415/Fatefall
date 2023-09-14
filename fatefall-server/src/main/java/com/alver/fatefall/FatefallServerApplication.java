package com.alver.fatefall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@EntityScan("com.alver.fatefall.data.entity")
@PropertySource("classpath:/fatefall-server.properties")
@ImportResource(value="classpath:/hsql_cfg.xml")
public class FatefallServerApplication {

    private static final Logger log = LoggerFactory.getLogger(FatefallServerApplication.class);

    public static void main(String... args) {
        new SpringApplicationBuilder(FatefallServerApplication.class).run();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        log.warn("Returning HTTP 400 Bad Request", e);
    }

}
