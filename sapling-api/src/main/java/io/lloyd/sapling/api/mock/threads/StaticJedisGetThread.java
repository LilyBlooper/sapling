package io.lloyd.sapling.api.mock.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.lloyd.sapling.api.mock.StaticRedisPool;

/**
 * 简单的进行Jedis的get操作<br/>
 * Created by LilyBlooper(lilyblooper@163.com) on 2018/3/5.
 */
public class StaticJedisGetThread implements Runnable {

	private int round;
	private String name;
	private CountDownLatch startLatch;
	private CountDownLatch workerLatch;

	public StaticJedisGetThread(int round, CountDownLatch startLatch, CountDownLatch workerLatch,
			String name) {
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
			for (int i = 0; i < round; i++) {
				StaticRedisPool.doOnce(kvList.get(i));
			}
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
