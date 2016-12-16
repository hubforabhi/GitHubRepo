package com.abhi.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class MessageDispatcherJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(MessageDispatcherJob.class);
			
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			ApplicationContext appContext = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
		} catch (SchedulerException e) {
		}
		logger.debug("Quartz Zob JobDataMap Size "+context.getMergedJobDataMap().values().size());
	}
}
