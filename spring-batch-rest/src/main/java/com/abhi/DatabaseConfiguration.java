package com.abhi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Base64Utils;

@Configuration
public class DatabaseConfiguration {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean
	public DataSource dataSource() {		
		return DataSourceBuilder.create().
				driverClassName(env.getProperty("datasource.driver-class-name")).
				username(env.getProperty("datasource.username")).
				password(new String(Base64Utils.decodeFromString(env.getProperty("datasource.password")))).
				url(env.getProperty("datasource.url")).build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcNTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	@Bean
	public DataSource reportingDataSource() {		
		return DataSourceBuilder.create().
				driverClassName(env.getProperty("datasource.driver-class-name")).
				username(env.getProperty("datasource.username")).
				password(new String(Base64Utils.decodeFromString(env.getProperty("datasource.password")))).
				url(env.getProperty("datasource.url")).build();
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
