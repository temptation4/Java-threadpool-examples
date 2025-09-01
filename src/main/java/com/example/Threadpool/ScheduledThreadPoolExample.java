package com.example.Threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        // Create a ScheduledThreadPool with 2 threads
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // 1️⃣ Run a task once after a delay of 3 seconds
        scheduler.schedule(() -> {
            System.out.println("Delayed Task executed by: " + Thread.currentThread().getName());
        }, 3, TimeUnit.SECONDS);

        // 2️⃣ Run a task repeatedly at a fixed rate
        // - Initial delay: 2 seconds
        // - Run every 5 seconds
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("FixedRate Task executed by: " + Thread.currentThread().getName());
        }, 2, 5, TimeUnit.SECONDS);

        // 3️⃣ Run a task repeatedly with fixed delay between tasks
        // - Initial delay: 1 second
        // - Wait 4 seconds after each task finishes
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("FixedDelay Task executed by: " + Thread.currentThread().getName());
        }, 1, 4, TimeUnit.SECONDS);

        // We don't call shutdown immediately here because tasks are periodic
        // (In real apps, you should call shutdown() when done)
    }
}
