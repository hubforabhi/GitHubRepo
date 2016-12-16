package com.abhi.job.provider;

import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.abhi.domain.job.User;

public class UserItemSqlSourceProvider implements ItemSqlParameterSourceProvider<User> {

	@Override
	public SqlParameterSource createSqlParameterSource(User user) {		
       MapSqlParameterSource sps = new MapSqlParameterSource();
       sps.addValue("username", user.getFirstName() + user.getLastName());       
       return sps;
	}
}
