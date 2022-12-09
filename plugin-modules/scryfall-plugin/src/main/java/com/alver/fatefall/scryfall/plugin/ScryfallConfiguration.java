package com.alver.fatefall.scryfall.plugin;

import com.alver.fatefall.app.fx.components.FXMLAutoLoader;
import com.alver.fatefall.app.plugin.implementations.cardview.DefaultCardView;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class ScryfallConfiguration {

    @Bean
    public SayHelloAction getSayHelloAction() {
        return new SayHelloAction();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DefaultCardView getDefaultCardView() {
        return new DefaultCardView();
    }
    @Bean
    public FXMLAutoLoader getFXMLAutoLoader(){
        return new FXMLAutoLoader();
    }
}

