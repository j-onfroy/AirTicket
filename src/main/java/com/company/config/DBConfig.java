package com.company.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DBConfig {
    @Value("${db1.url}")
    private String url;
    @Value("${db1.username}")
    private String username;
    @Value("${db1.password}")
    private String password;
    @Value("${db1.driver}")
    private String dbManager;

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplateFirst() {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(dbManager);
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
