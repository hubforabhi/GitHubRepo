package com.abhi.service;

import java.util.List;

public interface ApplicationConfigService {
	public List<String> getParamValues(String configParamName, String configParamGroup);
}
