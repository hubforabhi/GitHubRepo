package com.abhi.mockito.service;

import org.springframework.stereotype.Repository;

@Repository
public class DataServiceImpl implements DataService {
	@Override
	public int[] getData() {
		return new int[] {1,2,3,4,5};
	}
}
