package com.form3.hometest.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Value("${db.endpoint}")
    private String dbEndpoint;

    @PostConstruct
    public void initializeDatabase() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.migrate();
    }

    @Bean("dataSource")
    public DataSource dataSource() { return createDatasource(); }

    private DataSource createDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl(dbEndpoint);
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

}
