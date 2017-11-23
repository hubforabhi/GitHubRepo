package com.abhi.audit.repository.impl;

import java.sql.Date;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.audit.repository.AuditRepository;

@Repository
public class AuditRepositoryImpl implements AuditRepository {
	private static Logger logger = LoggerFactory.getLogger(AuditRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate template;
	
	@Transactional
	public boolean insertAuditRecord() {
		logger.debug("AuditRepositoryImpl.insertAuditRecord");
		int rowCount = template.update("insert into cdisi_audit values(?,?,?)", new Object[]{1,"Sample Audit", new Date(new java.util.Date().getTime())});
		return true;
	}
	
	@Transactional(value="transactionManager" , isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean insertAuditErrorRecord() {
		return true;
	}
}
