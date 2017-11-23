package com.abhi.audit.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.audit.repository.AuditRepository;
import com.abhi.audit.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService {
	private static Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
	
	@Autowired
	private AuditRepository repo;
	
	@Override
	public boolean insertAuditRecord() {
		logger.debug("AuditServiceImpl.insertAuditRecord");
		repo.insertAuditRecord();
		return true;
	}

	@Override
	public boolean insertAuditErrorRecord() {
		return true;
	}

}
