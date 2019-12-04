package com.abhi.redis.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.abhi.redis.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Student> findAll() {
		return jdbcTemplate.query("SELECT * FROM STUDENT", new RowMapper<Student>() {
		@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(rs.getInt("ID"));
				student.setName(rs.getString("NAME"));
				return student;
			}	
		});
	}
	
	public Optional<Student> findById(long id) {
		Student student = jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID=?", new Object[] {id} , new RowMapper<Student>() {
		@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(rs.getInt("ID"));
				student.setName(rs.getString("NAME"));
				return student;
			}	
		});
		return Optional.of(student);
	}
}