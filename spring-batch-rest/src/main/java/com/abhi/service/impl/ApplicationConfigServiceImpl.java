package com.abhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.domain.ApplicationConfigPK;
import com.abhi.repository.ApplicationConfigRepository;
import com.abhi.service.ApplicationConfigService;

@Service
public class ApplicationConfigServiceImpl implements ApplicationConfigService {

	@Autowired
	private ApplicationConfigRepository applicationConfigRepository;
	
	@Transactional
	public List<String> getParamValues(String configParamName, String configParamGroup) {
		ApplicationConfigPK appConfigPk = new ApplicationConfigPK();
		appConfigPk.setConfigParamName(configParamName);
		appConfigPk.setConfigParamGroup(configParamGroup);
		return applicationConfigRepository.findOne(appConfigPk).getConfigParamValues();
	}


	public void setApplicationConfigRepository(ApplicationConfigRepository applicationConfigRepository) {
		this.applicationConfigRepository = applicationConfigRepository;
	}
}
