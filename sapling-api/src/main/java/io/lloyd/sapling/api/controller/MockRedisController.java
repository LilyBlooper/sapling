package io.lloyd.sapling.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 测试配置多个redis连接池<br>
 * Created by LilyBlooper(lilyblooper@163.com) on 2018/3/5.
 */

@RestController
public class MockRedisController {

	@Value("${foo.bar}")
	String foo;

	@Autowired
	private JedisPool biz1JedisPool;

	@Autowired
	private JedisPool biz2JedisPool;

	@Autowired
	private JedisPool biz3JedisPool;

	@RequestMapping("/biz1")
	public String biz1() {
		Jedis one = biz1JedisPool.getResource();
		one.setex("biz1", 60, "biz1");

		one.close();
		return foo;
	}

	@RequestMapping("/biz2")
	public String biz2() {
		Jedis one = biz2JedisPool.getResource();
		one.setex("biz2", 60, "biz2");
		one.close();
		return foo;
	}

	@RequestMapping("/biz3")
	public String biz3() {
		Jedis one = biz3JedisPool.getResource();
		one.setex("biz3", 60, "biz3");
		one.close();
		return foo;
	}
}
