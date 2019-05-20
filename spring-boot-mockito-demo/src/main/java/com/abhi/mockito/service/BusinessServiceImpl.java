package com.abhi.mockito.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
	@Autowired 
	private DataService dataService;

	@Override
	public int getGreatest() {
		return Arrays.stream(dataService.getData()).max().getAsInt();
	}
}
