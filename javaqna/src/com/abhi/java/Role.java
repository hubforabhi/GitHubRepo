package com.abhi.java;

import java.util.EnumSet;

public enum Role {
	SELF(EnumSet.of(Operation.VIEW_PROFILE, Operation.EDIT_PROFILE, Operation.VIEW_COMMENTS)),
	FRIEND(EnumSet.of(Operation.VIEW_PROFILE, Operation.VIEW_COMMENTS)),
	GUEST(EnumSet.of(Operation.VIEW_PROFILE));
	
	EnumSet<Operation> operationSet;
	Role(EnumSet<Operation> operationSet) {
		this.operationSet = operationSet;
	}
}