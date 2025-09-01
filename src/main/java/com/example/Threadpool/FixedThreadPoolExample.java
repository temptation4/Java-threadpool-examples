package com.example.Threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed-size thread pool with 3 threads
        // - At most 3 tasks will run in parallel
        // - Remaining tasks will wait in the queue
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 6 tasks to the executor
        // Even though we submit 6 tasks, only 3 run at a time
        for (int i = 1; i <= 6; i++) {
            int taskId = i; // capture the task number for printing
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread: "
                        + Thread.currentThread().getName());
                try {
                    // Simulate task execution time
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restore interrupt status
                }
                System.out.println("Task " + taskId + " completed by thread: "
                        + Thread.currentThread().getName());
            });
        }

        // Shutdown the executor:
        // - It will stop accepting new tasks
        // - Already submitted tasks will continue to run
        executor.shutdown();

        try {
            // Wait for all tasks to finish (with timeout 1 minute)
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow(); // Force shutdown if tasks didn’t finish
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks completed. Executor is shut down.");
    }
}


