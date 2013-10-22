package com.kubrynski.data.config;

import com.kubrynski.data.repository.generic.GenericRepositoryFactoryBean;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-03-30
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.kubrynski.data.repository", repositoryFactoryBeanClass = GenericRepositoryFactoryBean.class)
@EnableTransactionManagement
public class DataConfig {

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseFactoryBean databaseFactoryBean = new EmbeddedDatabaseFactoryBean();
    databaseFactoryBean.setDatabaseType(EmbeddedDatabaseType.H2);
    databaseFactoryBean.afterPropertiesSet();
    return databaseFactoryBean.getObject();
  }

  @Bean
  public AbstractEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dataSource());
    entityManagerFactory.setPackagesToScan("com.kubrynski.data.model");
    entityManagerFactory.setPersistenceProvider(new HibernatePersistence());
    entityManagerFactory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create-drop");
    entityManagerFactory.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    entityManagerFactory.getJpaPropertyMap().put("hibernate.show_sql", "true");
    entityManagerFactory.afterPropertiesSet();

    return entityManagerFactory;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory().getObject());
  }

}
