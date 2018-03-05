package io.lloyd.sapling.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 此处配置单节点的redis，默认都使用连接池<br/>
 *
 * Created by LilyBlooper(lilyblooper@163.com) on 2018/3/2.
 */

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {
	private Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Bean
	public JedisPool biz1JedisPool(@Value("${redis.biz1.host}") String host,
			@Value("${redis.biz1.port}") int port, @Value("${redis.biz1.timeout}") int timeout,
			@Value("${redis.biz1.auth}") String auth,
			@Value("${redis.biz1.needAuth}") boolean needAuth,
			@Value("${redis.biz1.pool.maxTotal}") int maxTotal,
			@Value("${redis.biz1.pool.maxIdle}") int maxIdle,
			@Value("${redis.biz1.pool.maxWaitMillis}") int maxWaitMillis,
			@Value("${redis.biz1.pool.testOnBorrow}") boolean testOnBorrow,
			@Value("${redis.biz1.pool.testOnReturn}") boolean testOnReturn) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setTestOnBorrow(testOnBorrow);
		poolConfig.setTestOnReturn(testOnReturn);
		JedisPool biz1JedisPool;
		if (needAuth) {
			biz1JedisPool = new JedisPool(poolConfig, host, port, timeout, auth);
			logger.info("biz1JedisPool inited with auth");
		} else {
			biz1JedisPool = new JedisPool(poolConfig, host, port, timeout);
			logger.info("biz1JedisPool inited");
		}
		return biz1JedisPool;
	}

	@Bean
	public JedisPool biz2JedisPool(@Value("${redis.biz2.host}") String host,
			@Value("${redis.biz2.port}") int port, @Value("${redis.biz2.timeout}") int timeout,
			@Value("${redis.biz2.auth}") String auth,
			@Value("${redis.biz2.needAuth}") boolean needAuth,
			@Value("${redis.biz2.pool.maxTotal}") int maxTotal,
			@Value("${redis.biz2.pool.maxIdle}") int maxIdle,
			@Value("${redis.biz2.pool.maxWaitMillis}") int maxWaitMillis,
			@Value("${redis.biz2.pool.testOnBorrow}") boolean testOnBorrow,
			@Value("${redis.biz2.pool.testOnReturn}") boolean testOnReturn) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setTestOnBorrow(testOnBorrow);
		poolConfig.setTestOnReturn(testOnReturn);
		JedisPool biz1JedisPool;
		if (needAuth) {
			biz1JedisPool = new JedisPool(poolConfig, host, port, timeout, auth);
			logger.info("biz2JedisPool inited with auth");
		} else {
			biz1JedisPool = new JedisPool(poolConfig, host, port, timeout);
			logger.info("biz2JedisPool inited");
		}
		return biz1JedisPool;
	}

	@Bean
	public JedisPool biz3JedisPool(@Value("${redis.biz3.host}") String host,
			@Value("${redis.biz3.port}") int port, @Value("${redis.biz3.timeout}") int timeout,
			@Value("${redis.biz3.auth}") String auth,
			@Value("${redis.biz3.needAuth}") boolean needAuth,
			@Value("${redis.biz3.pool.maxTotal}") int maxTotal,
			@Value("${redis.biz3.pool.maxIdle}") int maxIdle,
			@Value("${redis.biz3.pool.maxWaitMillis}") int maxWaitMillis,
			@Value("${redis.biz3.pool.testOnBorrow}") boolean testOnBorrow,
			@Value("${redis.biz3.pool.testOnReturn}") boolean testOnReturn) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setTestOnBorrow(testOnBorrow);
		poolConfig.setTestOnReturn(testOnReturn);
		JedisPool biz1JedisPool;
		if (needAuth) {
			biz1JedisPool = new JedisPool(poolConfig, host, port, timeout, auth);
			logger.info("biz3JedisPool inited with auth");
		} else {
			biz1JedisPool = new JedisPool(poolConfig, host, port, timeout);
			logger.info("biz3JedisPool inited");
		}
		return biz1JedisPool;
	}

}
