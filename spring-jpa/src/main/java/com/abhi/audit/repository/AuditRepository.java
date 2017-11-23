package com.abhi.audit.repository;

public interface AuditRepository {
	public boolean insertAuditRecord();
	public boolean insertAuditErrorRecord();
}
