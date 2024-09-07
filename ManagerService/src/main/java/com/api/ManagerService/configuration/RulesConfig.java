package com.api.ManagerService.configuration;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "com.ManagerService.rules.IneligibleRules")
public class RulesConfig {
    @Bean
    @Scope("prototype")
    public Rules rules() throws IOException, ClassNotFoundException {
        Rules rules = new Rules();

        return rules;
    }
    @Bean
    public DefaultRulesEngine rulesEngine() {
        return new DefaultRulesEngine();
    }

}
