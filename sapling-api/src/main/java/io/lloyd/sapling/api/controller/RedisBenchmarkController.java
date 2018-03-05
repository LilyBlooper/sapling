package io.lloyd.sapling.api.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.lloyd.sapling.api.mock.threads.JedisGetThread;

/**
 * benchmark 控制器，进行redis的相关压测 </br>
 * Created by LilyBlooper(lilyblooper@163.com) on 2018/3/5.
 */
@RestController
@RequestMapping("/benchmark")
public class RedisBenchmarkController {

	@RequestMapping("test2")
	public String benchmark2() {
		// 使用固定个数的线程进行redis get测试
		int threadSize = 16;
		int round = 10000;
		ExecutorService pool = Executors.newFixedThreadPool(threadSize);
		CountDownLatch mainLatch = new CountDownLatch(1);
		CountDownLatch workerLatch = new CountDownLatch(threadSize);
		long startTime = 0;
		try {
			for (int i = 0; i < threadSize; i++) {
				pool.submit(new JedisGetThread(round, mainLatch, workerLatch, "苦工" + i));
			}
			startTime = System.nanoTime();
			mainLatch.countDown();
			workerLatch.await();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			long endTime = System.nanoTime();
			long elapsed = (endTime - startTime) / 1000 / 1000;
			pool.shutdown();
			return "ok,costs: " + elapsed + "millis";
		}
	}

}
