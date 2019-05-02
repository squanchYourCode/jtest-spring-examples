package com.parasoft.examples.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ClassConfigControllerTest dependencies
 */
@Configuration
class ConfigClass
{
    @Bean
    public ClassConfigController getClassConfigController()
    {
        return new ClassConfigController();
    }
}
