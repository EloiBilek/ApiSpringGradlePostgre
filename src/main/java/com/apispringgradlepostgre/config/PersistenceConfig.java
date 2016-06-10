/**
 * 
 */
package com.apispringgradlepostgre.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.apispringgradlepostgre.model.entity.User;
import com.google.common.base.Preconditions;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author eloi.bilek
 *
 */
@Configuration
@PropertySources(value = { @PropertySource("classpath:application.properties") })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.apispringgradlepostgre.model.repository")
public class PersistenceConfig {

	@Autowired
	private Environment env;

	public PersistenceConfig() {
		super();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory factory = entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public DataSource dataSource() {
		final ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
		} catch (PropertyVetoException e) {
			// TODO implementar log4j
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
		dataSource.setUser(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
		dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));
		dataSource.setAcquireIncrement(Integer.parseInt(Preconditions.checkNotNull(env.getProperty("c3p0.acquireIncrement"))));
		dataSource.setMinPoolSize(Integer.parseInt(Preconditions.checkNotNull(env.getProperty("c3p0.minPoolSize"))));
		dataSource.setMaxPoolSize(Integer.parseInt(Preconditions.checkNotNull(env.getProperty("c3p0.maxPoolSize"))));
		dataSource.setTestConnectionOnCheckout(Boolean.parseBoolean(Preconditions.checkNotNull(env.getProperty("c3p0.testConnection"))));

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { User.class.getPackage().getName() });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

		return hibernateProperties;
	}
}
