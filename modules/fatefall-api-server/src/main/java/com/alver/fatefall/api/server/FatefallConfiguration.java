package com.alver.fatefall.api.server;

import mse.MseCliProcess;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class FatefallConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public MseCliProcess getMagicSetEditorProcess() throws IOException {
        return new MseCliProcess();
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        DataSize BIG_AS_FUCK = DataSize.of(124, DataUnit.TERABYTES);

        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(BIG_AS_FUCK);
        factory.setMaxRequestSize(BIG_AS_FUCK);
        return factory.createMultipartConfig();
    }
}
