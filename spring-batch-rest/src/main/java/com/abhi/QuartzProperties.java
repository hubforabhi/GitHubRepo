package com.abhi;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.quartz", ignoreUnknownFields=false)
public class QuartzProperties {
	
	@NotNull
	private JobStore jobStore;

	@NotNull
	private String jobStoreClassName;

	public static class JobStore {		
		private String delegateClassName;
		private String dataSource;
		
		public String getDelegateClassName() {
			return delegateClassName;
		}

		public void setDelegateClassName(String delegateClassName) {
			this.delegateClassName = delegateClassName;
		}

		public String getDataSource() {
			return dataSource;
		}

		public void setDataSource(String dataSource) {
			this.dataSource = dataSource;
		}
	}
	
	public String getJobStoreClassName() {
		return jobStoreClassName;
	}

	public void setJobStoreClassName(String jobStoreClassName) {
		this.jobStoreClassName = jobStoreClassName;
	}

	public JobStore getJobStore() {
		return jobStore;
	}

	public void setJobStore(JobStore jobStore) {
		this.jobStore = jobStore;
	}
}
