package com.skill.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.skill")
public class AppConfig {
    // This class sets up component scanning for any class annotated with @Component, @Service, etc.
}
