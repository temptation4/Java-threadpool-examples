package com.example.Threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {
    public static void main(String[] args) {
        // Create a CachedThreadPool
        // - Creates new threads as needed
        // - Reuses previously created threads if available
        // - Removes threads that are idle for 60 seconds
        ExecutorService executor = Executors.newCachedThreadPool();

        // Submit multiple short tasks
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread: "
                        + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Shutdown the executor after tasks finish
        executor.shutdown();
    }
}
