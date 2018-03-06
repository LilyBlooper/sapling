package io.lloyd.sapling.api.mock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 静态全局的jedis连接池<br>
 * Created by LilyBlooper(lilyblooper@163.com) on 2018/3/5.
 */
public class StaticRedisPool {
	private static JedisPool pool;

	static {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(500);
		poolConfig.setMaxIdle(10);
		poolConfig.setMaxWaitMillis(1000);
		poolConfig.setTestOnBorrow(false);
		poolConfig.setTestOnReturn(false);
		poolConfig.setTestWhileIdle(false);
		pool = new JedisPool(poolConfig, "127.0.0.1", 6379, 2000);
	}

	public static JedisPool getPool() {
		return pool;
	}

	public static void doOnce(String kv) {
		Jedis jedis = StaticRedisPool.getPool().getResource();
		jedis.set(kv, kv);
		jedis.close();
	}
}
