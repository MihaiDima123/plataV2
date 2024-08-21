package com.plata.Plata.core.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {
    @Value("${app.datasource.min-idle}")
    private int MIN_IDLE_CONNECTIONS;

    @Value("${app.datasource.conn-leak-detection-threshold}")
    private int LEAK_DETECTION_THRESHOLD;

    @Value("${app.datasource.max-pool-size}")
    private int MAX_CONNECTION_POOL_SIZE;

    @Value("${spring.datasource.user}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String  PASSWORD;

    @Value("${spring.datasource.jdbc-url}")
    private String URL;

    @Value("${spring.datasource.driver-classname}")
    private String DRIVER_CLASSNAME;

    @Bean
    @FlywayDataSource
    public DataSource dataSource() {
        final var datasource = new HikariDataSource();

        datasource.setDriverClassName(DRIVER_CLASSNAME);
        datasource.setUsername(USERNAME);
        datasource.setPassword(PASSWORD);
        datasource.setJdbcUrl(URL);

        datasource.setMinimumIdle(MIN_IDLE_CONNECTIONS);
        datasource.setMaximumPoolSize(MAX_CONNECTION_POOL_SIZE);
        datasource.setLeakDetectionThreshold(LEAK_DETECTION_THRESHOLD);

        return datasource;
    }
}
