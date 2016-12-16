package com.abhi;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableConfigurationProperties(QuartzProperties.class)
public class QuartzConfiguration {
	private static Logger logger = LoggerFactory.getLogger(QuartzConfiguration.class);

	@Autowired
	private QuartzProperties qProps;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean sfBean = new SchedulerFactoryBean();
		sfBean.setDataSource(dataSource);
		sfBean.setTransactionManager(transactionManager);
		sfBean.setApplicationContextSchedulerContextKey("applicationContext");

		Properties quartzProperties = new Properties();
		quartzProperties.put("org.quartz.jobStore.class", qProps.getJobStoreClassName());
		quartzProperties.put("org.quartz.jobStore.driverDelegateClass", qProps.getJobStore().getDelegateClassName());
		quartzProperties.put("org.quartz.jobStore.dataSource", qProps.getJobStore().getDataSource());
		sfBean.setQuartzProperties(quartzProperties);
		return sfBean;
	}
}
