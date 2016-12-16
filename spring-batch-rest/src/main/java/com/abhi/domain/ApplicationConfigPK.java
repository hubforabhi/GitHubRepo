package com.abhi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApplicationConfigPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7030729006851563195L;

	@Column(name="CONFIG_PARAM_NAME")
	private String configParamName;
	
	@Column(name="CONFIG_PARAM_GROUP")
	private String configParamGroup;

	public String getConfigParamName() {
		return configParamName;
	}

	public void setConfigParamName(String configParamName) {
		this.configParamName = configParamName;
	}

	public String getConfigParamGroup() {
		return configParamGroup;
	}

	public void setConfigParamGroup(String configParamGroup) {
		this.configParamGroup = configParamGroup;
	}
}