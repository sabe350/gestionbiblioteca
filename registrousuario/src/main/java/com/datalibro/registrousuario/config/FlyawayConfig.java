package com.datalibro.registrousuario.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyawayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource){
        return Flyway.configure().dataSource(dataSource).load();
    }
}
