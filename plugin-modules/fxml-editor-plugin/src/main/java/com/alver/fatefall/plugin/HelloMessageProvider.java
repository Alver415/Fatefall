package com.alver.fatefall.plugin;

import org.springframework.stereotype.Component;

@Component
public class HelloMessageProvider implements MessageProvider {
    public String getMessage() {
        return "Hello";
    }
}
