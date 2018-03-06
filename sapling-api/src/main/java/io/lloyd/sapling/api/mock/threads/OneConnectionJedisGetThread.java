package io.lloyd.sapling.api.mock.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.lloyd.sapling.api.mock.StaticRedisPool;
import redis.clients.jedis.Jedis;

/**
 * 简单的进行Jedis的get操作<br/>
 * Created by LilyBlooper(lilyblooper@163.com) on 2018/3/5.
 */
public class OneConnectionJedisGetThread implements Runnable {

	private int round;
	private String name;
	private CountDownLatch startLatch;
	private CountDownLatch workerLatch;

	public OneConnectionJedisGetThread(int round, CountDownLatch startLatch,
			CountDownLatch workerLatch, String name) {
		this.round = round;
		this.startLatch = startLatch;
		this.workerLatch = workerLatch;
		this.name = name;
	}

	@Override
	public void run() {
		List<String> kvList = genKV();
		try {
			startLatch.await();
			Jedis jedis = StaticRedisPool.getPool().getResource();
			for (int i = 0; i < round; i++) {
				jedis.set(kvList.get(i), kvList.get(i));
			}
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerLatch.countDown();
		}
	}

	/**
	 * 准确测量，省去了时间
	 * 
	 * @return
	 */
	private List<String> genKV() {
		List<String> kvList = new ArrayList<>();
		for (int i = 0; i < round; i++) {
			kvList.add(name + ":" + i);
		}
		return kvList;
	}

}
