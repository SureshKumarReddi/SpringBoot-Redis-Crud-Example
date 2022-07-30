package com.Suresh.RedisConfig;

import org.springframework.context.annotation.Bean;

//https://www.onlinetutorialspoint.com/spring-boot/spring-boot-redis-data-example-crud-operations.html

//https://stackabuse.com/spring-boot-with-redis-hashoperations-crud-functionality/

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.Suresh.Models.Item;

@Configuration
public class RedisConfig {
	
	@SuppressWarnings("deprecation")
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		 JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
	        jedisConnectionFactory.setHostName("localhost");
	        jedisConnectionFactory.setPort(6379);
	        jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Item> redisTemplate(){
		RedisTemplate<String, Item> template = new RedisTemplate<String,Item>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.afterPropertiesSet();
		return template;
	}
}