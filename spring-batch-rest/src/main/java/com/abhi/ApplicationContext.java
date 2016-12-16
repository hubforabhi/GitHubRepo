package com.abhi;

import static reactor.bus.selector.Selectors.$;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.abhi.consumer.BatchRequestProcessor;

import reactor.Environment;
import reactor.bus.EventBus;

@EnableTransactionManagement
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ApplicationContext implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
    
	@Autowired
	private EventBus eventBus;	
	
	@Autowired
	private BatchRequestProcessor requestProcessor;	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public BatchRequestProcessor requestProcessor() {
		return new BatchRequestProcessor();
	}
	
	@Bean
    public Environment env() {
        return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    public EventBus createEventBus(Environment env) {
	    return EventBus.create(env, Environment.THREAD_POOL);
    }
	
	@Override
	public void run(String... arg0) throws Exception {
		logger.debug("------------- CommandLineRunner.run");
		eventBus.on($("BatchRequestEvent"), requestProcessor);
	}
	
	@Bean
	public CommandLineRunner demo() {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				logger.debug("------------- New Bean CommandLineRunner.Run");
			}
		};
	}
	
	@PostConstruct
	public void initilize() {
/*		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);
			TrustManager tManager[] = tmf.getTrustManagers();
		} catch (KeyStoreException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		logger.debug("------------- ApplicationContext.initialize");
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		GenericBeanDefinition gbDef = new GenericBeanDefinition();
		gbDef.setBeanClass(org.apache.tomcat.jdbc.pool.DataSource.class);
		MutablePropertyValues props = new MutablePropertyValues();
		props.add("url", "jdbc:postgresql://localhost:5432/postgres?currentSchema=public");
		props.add("driverClassName","org.postgresql.Driver");
		props.add("username","postgres");
		props.add("password","postgres");
		gbDef.setPropertyValues(props);
		beanFactory.registerBeanDefinition("dataSource", gbDef);
		
		GenericBeanDefinition tmDef = new GenericBeanDefinition();
		tmDef.setBeanClass(DataSourceTransactionManager.class);
		props = new MutablePropertyValues();
		props.addPropertyValue("dataSource", new RuntimeBeanReference("dataSource")); 
		tmDef.setPropertyValues(props);
		beanFactory.registerBeanDefinition("transactionManager", tmDef);
		
		GenericApplicationContext parentAppContext  = new GenericApplicationContext(beanFactory);parentAppContext.refresh();
		org.springframework.context.ApplicationContext childAppContext = new ClassPathXmlApplicationContext(new String[]{"spring-batch-db-to-db.xml"},parentAppContext);
		JobLauncher childJobLauncher = childAppContext.getBean("jobLauncher", JobLauncher.class);
		Job childJob = childAppContext.getBean("userDBJob", Job.class);
		try {
			childJobLauncher.run(childJob, new JobParametersBuilder().addString("Hello", "World").toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
		} 	
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationContext.class, args);
		Object obj = new String("");
		accept(obj);
		/*double a1 = 0.3d;
		double a2 = 0.2d;
		System.out.println(a1-a2);
		BigDecimal big1 = new BigDecimal("0.03");
		BigDecimal big2 = new BigDecimal("0.02");
		System.out.println(big1.subtract(big2).toEngineeringString());
		System.out.println(big1.subtract(big2).toPlainString());*/
		
		List<String> empNameList = new ArrayList<>();
		//empNameList.stream().filter(empName -> empName.equalsIgnoreCase("abhi")).sorted(comparing)
	}
	
	public static void accept(Object obj){
		System.out.println("Object");
	}
	
	public static void accept(String str) {
		System.out.println("String");
	}
	
	//private static void $() {
	//}
}
