package com.abhi.actuator.healthindicator;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PostgreDBHealthIndicator implements HealthIndicator {
	private static final Logger logger  = LoggerFactory.getLogger(PostgreDBHealthIndicator.class);
	
	private static final String PG_IS_IN_RECOVERY_SQL = "select pg_is_in_recovery()";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Health health() {		
		if(jdbcTemplate.queryForObject(PG_IS_IN_RECOVERY_SQL, new RowMapper<Boolean> () {
			@Override
			public Boolean mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getBoolean(1);
			}
		})) {
			logger.error("PostgreSQL DB is recovering");
			return Health.down().withDetail("Error Code", "PostgreSQL DB is recovering").build();
		}		
		return Health.up().build();
	}
}