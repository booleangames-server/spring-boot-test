package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean(name = "global")
    @ConfigurationProperties(prefix = "spring.datasource.global")
    public DataSource globalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "user1")
    @ConfigurationProperties(prefix = "spring.datasource.user1")
    public DataSource user1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "user2")
    @ConfigurationProperties(prefix = "spring.datasource.user2")
    public DataSource user2DataSource() {
        return DataSourceBuilder.create().build();
    }
}