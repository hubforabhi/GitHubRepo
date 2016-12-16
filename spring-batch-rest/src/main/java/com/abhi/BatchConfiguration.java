package com.abhi;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration extends DefaultBatchConfigurer {
	private static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);	
	
/*	@Bean
	public JobRepository jobRepository() throws Exception {
		return new MapJobRepositoryFactoryBean().getObject();
	}

	@Bean
	public JobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		return jobLauncher;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}
*/
	@Bean
	public ItemReader<String> reader() {
		return new ItemReader<String>() {
			@Override
			public String read()
					throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				logger.debug("-------------------------- NOTHING TO READ ----------------------------------");
				return null;
			}
		};
	}
	
	@Bean 
	public ItemWriter<String> writer() {
		return new ItemWriter<String>() {
			@Override
			public void write(List<? extends String> arg0) throws Exception {
				logger.debug("-------------------------- NOTHING TO WRITE ----------------------------------");
			}
		};
	}

	@Bean
	public Step testStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<String> reader, ItemWriter<String> writer) {
		return new StepBuilderFactory(jobRepository, transactionManager).get("testStep").
				<String, String> chunk(10).reader(reader).writer(writer).build();
	}

	@Bean
	public Job testJob(JobRepository jobRepository, Step step) {
		return new JobBuilderFactory(jobRepository).get("testJob").start(step).build();
	}
	
	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}	
}