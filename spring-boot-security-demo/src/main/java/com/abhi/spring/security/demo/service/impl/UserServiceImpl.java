package com.abhi.spring.security.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.spring.security.demo.domain.User;
import com.abhi.spring.security.demo.domain.view.UserView;
import com.abhi.spring.security.demo.mapper.Mapper;
import com.abhi.spring.security.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private Mapper<User, UserView> mapper;
	
	@Override
	public UserView createUser(UserView userView) {
		User user = mapper.mapTo(userView);		
		return mapper.mapFrom(user);
	}
}
