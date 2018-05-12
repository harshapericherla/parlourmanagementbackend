package com.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.RestError;
import com.serivcereport.beans.ServiceReportBean;
import com.serivcereport.beans.ServicesBean;
import com.serivcereport.beans.StaffBean;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@Bean
	public SessionFactory getSessionFactory(){
		
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		Class classes[] = new Class[]{ClientBean.class,ServicesBean.class,StaffBean.class,ServiceReportBean.class,RestError.class};
		lsf.addAnnotatedClasses(classes);
		return lsf.buildSessionFactory();
	}
	
	
	public DataSource getDataSource(){
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.h2.Driver");
		basicDataSource.setUrl("jdbc:h2:tcp://localhost/~/parlourdev");
		basicDataSource.setUsername("harsha");
		basicDataSource.setPassword("harsha123");
		return basicDataSource;
	}
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
        return transactionManager;
    }
	
	@Bean
	public EntityManager entityManager(){
		return entityManagerFactory().getObject().createEntityManager();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan("com.*");
		em.setJpaVendorAdapter(adapter);
		return em;
	}
	
}
