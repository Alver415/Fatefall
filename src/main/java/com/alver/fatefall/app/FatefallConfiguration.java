package com.alver.fatefall.app;

import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.persistence.repositories.WorkspaceRepository;
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
    public WorkspaceRepository workspaceRepository;

    @Bean
    public ObservableList<Workspace> getWorkspaces() {
        ObservableList<Workspace> list = FXCollections.observableArrayList();
        workspaceRepository.findAll().forEach(list::add);
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