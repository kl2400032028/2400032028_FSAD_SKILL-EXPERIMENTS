package com.skill.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * SKILL-4 — Annotation Configuration
 *
 * Java-based Spring configuration class.
 * @Configuration marks this as the Spring configuration source.
 * @ComponentScan tells Spring to scan the "com.skill.annotation" package
 * for @Component, @Service, @Repository etc. beans.
 */
@Configuration
@ComponentScan(basePackages = "com.skill.annotation")
public class AppConfig {
    // No explicit @Bean definitions needed here —
    // Spring auto-discovers Student via @Component scan.
}
