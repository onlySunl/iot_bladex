package org.springblade.core.tds.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * TDS 配置
 *
 * @author Chill
 */
@Configuration
@ConditionalOnProperty(prefix = "blade.tds", name = "enabled", havingValue = "true", matchIfMissing = false)
public class TdsConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "blade.tds")
    public TdsProperties tdsProperties() {
        return new TdsProperties();
    }

    @Bean("tdsDataSource")
    public DataSource tdsDataSource(TdsProperties properties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean("tdsJdbcTemplate")
    public JdbcTemplate tdsJdbcTemplate(DataSource tdsDataSource) {
        return new JdbcTemplate(tdsDataSource);
    }
}
