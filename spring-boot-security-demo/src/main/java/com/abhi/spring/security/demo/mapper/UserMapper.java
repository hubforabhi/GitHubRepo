package com.abhi.spring.security.demo.mapper;

import org.springframework.stereotype.Component;

import com.abhi.spring.security.demo.domain.User;
import com.abhi.spring.security.demo.domain.view.UserView;

@Component
public class UserMapper implements Mapper<User, UserView> {
	@Override
	public UserView mapFrom(User i) {
		return new UserView("123");
	}

	@Override
	public User mapTo(UserView o) {
		return new User();
	}
}
