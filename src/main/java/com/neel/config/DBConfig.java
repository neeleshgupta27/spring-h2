package com.neel.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class DBConfig {

	@Value("${db.driver}")
	private String DRIVER;
	
	@Value("${db.username}")
	private String USERNAME;
	
	@Value("${db.password}")
	private String PASSWORD;
	
	@Value("${db.url}")
	private String URL;
	
	@Value("${hibernate.dialect}")
	private String DIALECT;
	
	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;
	
	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;
	
	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;
	
	@Bean (name ="defDS")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setUrl(URL);
		return dataSource;
	}
	
	@Bean( name="defEmf")
	public EntityManagerFactory entiryManagerFactory(@Qualifier("defDS") DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.H2);
		vendorAdapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		em.setPersistenceUnitName("TEST-PU");
		em.setPersistenceXmlLocation("classpath:persistence.xml");
		em.afterPropertiesSet();
		return em.getObject();
	}
	
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DIALECT);
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		properties.put("hibernate.current_session_context_class", "thread");
		return properties;
	}
	
}
