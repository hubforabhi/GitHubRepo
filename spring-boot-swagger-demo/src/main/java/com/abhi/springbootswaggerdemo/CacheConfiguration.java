package com.abhi.springbootswaggerdemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.spring.cache.HazelcastCacheManager;

//@Configuration
//@ConditionalOnProperty(prefix = "cache.enabled", name = "dynamic", havingValue="true")
//@EnableCaching
public class CacheConfiguration {
	
    //@Bean
    //HazelcastInstance hazelcastInstance() {
       // return HazelcastClient.newHazelcastClient();
    //}
    
	@Bean
    CacheManager cacheManager() {
        return new HazelcastCacheManager();//hazelcastInstance());
    }
 
}
