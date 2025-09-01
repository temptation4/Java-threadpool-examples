package com.example.Threadpool;

import java.util.concurrent.*;

public class ThreadpoolApplication {

	public static void main(String[] args) {

		// Create a bounded blocking queue with capacity = 2
		// This queue will temporarily hold tasks when all core threads are busy
		ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);

		// Create a ThreadPoolExecutor with custom configuration:
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				2,                  // corePoolSize → minimum number of threads always kept alive
				4,                  // maximumPoolSize → maximum number of threads that can be created
				10,                 // keepAliveTime → idle threads above corePoolSize will be terminated after 10 sec
				TimeUnit.SECONDS,   // unit for keepAliveTime
				queue,              // workQueue → holds tasks waiting to be executed
				new CustomThreadFactory(), // custom thread naming/creation
				new CustomRejecHandler()   // custom rejection handler (when queue + threads are full)
		);

		// Submit 7 tasks
		for (int i = 0; i < 7; i++) {
			threadPoolExecutor.submit(() -> {
				try {
					// Simulate work (task takes 5 seconds)
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				// Print which thread processed the task
				System.out.println("Task processed by " + Thread.currentThread().getName());
			});
		}

		// Shutdown after submitting all tasks
		threadPoolExecutor.shutdown();
	}
}
