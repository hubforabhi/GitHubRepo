package com.abhi.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.abhi.ApplicationConstants;

@Entity(name="APPLICATION_CONFIG")
public class ApplicationConfig {
	
	@EmbeddedId
	private ApplicationConfigPK applicationConfigPK;
	
	@Column(name="CONFIG_PARAM_VALUE")
	private String configParamValue;
	
	@Transient
	private List<String> configParamValues;

	public ApplicationConfigPK getApplicationConfigPK() {
		return applicationConfigPK;
	}

	public void setApplicationConfigPK(ApplicationConfigPK applicationConfigPK) {
		this.applicationConfigPK = applicationConfigPK;
	}

	public String getConfigParamValue() {
		return configParamValue;
	}

	public void setConfigParamValue(String configParamValue) {
		this.configParamValue = configParamValue;
	}

	public List<String> getConfigParamValues() {
		if(configParamValue != null && configParamValue.indexOf(ApplicationConstants.APP_CONFIG_SEPARATOR_CHAR) >1) {
			configParamValues = Arrays.asList(configParamValue.split(ApplicationConstants.APP_CONFIG_SEPARATOR_CHAR));
		}
		return configParamValues;
	}
}
