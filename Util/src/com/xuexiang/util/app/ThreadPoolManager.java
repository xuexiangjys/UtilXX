package com.xuexiang.util.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池，管理线程的工具类
 * 
 * @author xx
 */
public class ThreadPoolManager {
	private ExecutorService service;

	private ThreadPoolManager() {
		int num = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num * 2);
	}

	private static ThreadPoolManager manager;

	public static ThreadPoolManager getInstance() {
		if (manager == null) {
			manager = new ThreadPoolManager();
		}
		return manager;
	}

	public void addTask(Runnable runnable) {
		service.submit(runnable);
	}
}
