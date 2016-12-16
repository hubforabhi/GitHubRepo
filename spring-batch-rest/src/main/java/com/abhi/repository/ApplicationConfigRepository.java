package com.abhi.repository;

import org.springframework.data.repository.CrudRepository;

import com.abhi.domain.ApplicationConfig;
import com.abhi.domain.ApplicationConfigPK;

public interface ApplicationConfigRepository extends CrudRepository<ApplicationConfig, ApplicationConfigPK> {

}
