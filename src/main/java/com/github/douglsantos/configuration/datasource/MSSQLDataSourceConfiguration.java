package com.github.douglsantos.configuration.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "mssqlContainerEntityManagerFactoryBean",
        transactionManagerRef = "mssqlCransactionManager",
        basePackages = { "com.github.douglsantos.dataprovider.repository.response" }
)
@EnableTransactionManagement
public class MSSQLDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("simulate-mssql.datasource")
    public DataSourceProperties mssqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "simulate-mssql.datasource")
    public DataSource mssqlDataSource(@Qualifier("mssqlDataSourceProperties") DataSourceProperties properties) {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mssqlContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder, @Qualifier("mssqlDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        return builder
                .dataSource(dataSource)
                .packages("com.github.douglsantos.dataprovider.model.response")
                .persistenceUnit("pedidoResponse")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager mssqlCransactionManager(
            @Qualifier("mssqlContainerEntityManagerFactoryBean")EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

}
