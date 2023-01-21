package com.alver.fatefall.app;

import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.persistence.repositories.CardCollectionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FatefallConfiguration implements ApplicationContextAware {

    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Autowired
    public CardCollectionRepository cardCollectionRepository;

    @Bean
    public ObservableList<CardCollection> getCardCollections() {
        ObservableList<CardCollection> list = FXCollections.observableArrayList();
        cardCollectionRepository.findAll().forEach(list::add);
        return list;
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ObjectWriter getObjectWriter(ObjectMapper objectMapper){
        return objectMapper.writerWithDefaultPrettyPrinter();
    }

}