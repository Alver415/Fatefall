package com.alver.fatefall.plugin;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class FxmlEditorPlugin extends SpringPlugin {

    public FxmlEditorPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.err.println("FxmlEditorPlugin.start()");
    }

    @Override
    public void stop() {
        System.err.println("FxmlEditorPlugin.stop()");
        super.stop(); // to close applicationContext
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        applicationContext.register(FxmlEditorPluginConfiguration.class);
        applicationContext.refresh();

        return applicationContext;
    }


    @Extension(ordinal=1)
    public static class HelloGreeting implements ActionEventHandler {

        @Autowired
        private final MessageProvider messageProvider;

        @Autowired
        public HelloGreeting(final MessageProvider messageProvider) {
            this.messageProvider = messageProvider;
        }

        @Override
        public String getName() {
            return "Say Hello";
        }

        @Override
        public String getDescription() {
            return "Just Say Hello...";
        }

        @Override
        public void handle(ActionEvent event) {
           String message = messageProvider.getMessage();
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText(message);
           alert.show();

        }
    }
}
