package com.alver.fatefall;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/fatefall-app.properties")
public class BundledConfiguration {
}
