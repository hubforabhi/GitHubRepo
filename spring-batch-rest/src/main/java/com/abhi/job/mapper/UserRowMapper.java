package com.abhi.job.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.abhi.domain.job.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setFirstName(rs.getString("first_name"));		
		user.setLastName(rs.getString("last_name"));
		return user;
	}
}
