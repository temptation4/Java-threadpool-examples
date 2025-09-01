package com.example.Threadpool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        // Create a thread pool with only ONE thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submit multiple tasks
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            executor.submit(() -> {
                // Each task will be executed one after another (sequentially)
                System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed.");
            });
        }

        // Shutdown the executor service
        executor.shutdown();
    }
}
