package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.servicereport.dao.ErrorDaoRepository;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.*")
@EnableJpaRepositories(basePackageClasses = {ErrorDaoRepository.class})
public class WebAppConfig extends WebMvcConfigurerAdapter{


}
