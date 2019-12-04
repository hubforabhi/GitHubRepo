package com.abhi.redis.demo;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {org.redisson.spring.starter.RedissonAutoConfiguration.class, RedisAutoConfiguration.class})
@ComponentScan(basePackages="com.abhi.redis.service,com.abhi.redis.service")
public class SpringBootRedisDemoApplication {	
	
	//@Bean
	public CommandLineRunner newCommandLineRunner() {
		return args-> {
				Config config = new Config();
				config.useSingleServer().setAddress("127.0.0.1:6379");
				RedissonClient client = Redisson.create(config);
				RAtomicLong rLong = client.getAtomicLong("SimpleRedisLongObject");
				rLong.set(110067311147l);
				System.out.println(rLong.incrementAndGet());			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisDemoApplication.class, args);
	}
}