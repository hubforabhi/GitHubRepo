package com.abhi.consumer;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

import com.abhi.job.MessageDispatcherJob;
import com.abhi.service.ApplicationConfigService;

import reactor.bus.Event;
import reactor.fn.Consumer;

public class BatchRequestProcessor implements Consumer<Event<String>> {
	private static Logger logger = LoggerFactory.getLogger(BatchRequestProcessor.class);
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	private ApplicationConfigService applicationConfigService;
	
	@Override
	public void accept(Event<String> arg0) {
		JobParameters jobParams = new JobParametersBuilder().addString("Hello", "World").toJobParameters();
		applicationConfigService.getParamValues("CASH", "PAYMENT_MODE_VS_PAYMENT_INFORMATION").forEach(System.out::println);;
		try {
			JobExecution execution = jobLauncher.run(job, jobParams);
			logger.debug("BatchRequestProcessor Job Exit Desciption" + execution.getExitStatus().getExitDescription());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		
		JobDetail jobDetail = JobBuilder.newJob(MessageDispatcherJob.class).withIdentity("MessageDispatcherJob").build();
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/2 * 1/1 * ? *");
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).forJob(jobDetail).build();
		
/*		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(MessageDispatcherJob.class);
		JobDetail jobDetail = factoryBean.getObject();
		
		SimpleTrigger trigger = new SimpleTriggerFactoryBean().getObject();
		CronTrigger cronTrigger = new CronTriggerFactoryBean().getObject();//.setCronExpression("0 0 1 ? ? ?");
*/		
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
/*		GenericBeanDefinition gBeanDefnintion = new GenericBeanDefinition();
		
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("dataSource", gBeanDefnintion);
		
		ApplicationContext parentAppContext  = new GenericApplicationContext(beanFactory);
		ApplicationContext childAppContext = new ClassPathXmlApplicationContext(new String[]{""},parentAppContext);*/
		
	}
}
