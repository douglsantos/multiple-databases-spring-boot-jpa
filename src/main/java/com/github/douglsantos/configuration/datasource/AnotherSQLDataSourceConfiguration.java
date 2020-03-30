package com.github.douglsantos.configuration.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "anothersqlContainerEntityManagerFactoryBean",
        transactionManagerRef = "anothersqlCransactionManager",
        basePackages = { "com.github.douglsantos.dataprovider.repository.request" }
)
@EnableTransactionManagement
public class AnotherSQLDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("simulate-anothersql.datasource")
    public DataSourceProperties anothersqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "simulate-anothersql.datasource")
    public DataSource anothersqlDataSource(@Qualifier("anothersqlDataSourceProperties") DataSourceProperties properties) {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean anothersqlContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder, @Qualifier("anothersqlDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        return builder
                .dataSource(dataSource)
                .packages("com.github.douglsantos.dataprovider.model.request")
                .persistenceUnit("pedidoResponse")
                .properties(properties)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager anothersqlCransactionManager(
            @Qualifier("anothersqlContainerEntityManagerFactoryBean")EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

}
