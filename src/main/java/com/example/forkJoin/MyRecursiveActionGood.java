package com.example.forkJoin;

import java.util.concurrent.RecursiveAction;

/**
 * GOOD EXAMPLE:
 * Uses fork() + join().
 * fork() schedules a task asynchronously in the pool.
 * join() waits only when the result is needed.
 * This allows subtasks to run in PARALLEL across multiple threads.
 * Idle threads can "steal" forked tasks -> better load balancing.
 */
public class MyRecursiveActionGood extends RecursiveAction {
    private final long workload;

    public MyRecursiveActionGood(long workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload > 16) {
            long w1 = workload / 2;
            long w2 = workload - w1;

            MyRecursiveActionGood left  = new MyRecursiveActionGood(w1);
            MyRecursiveActionGood right = new MyRecursiveActionGood(w2);

            // ✅ GOOD: fork left so it can run asynchronously
            left.fork();

            // compute right directly (reduces overhead of forking both)
            right.compute();

            // wait for left to finish
            left.join();
        } else {
            doWork(workload);
        }
    }

    private void doWork(long w) {
        try {
            System.out.printf("[GOOD] Thread=%s doing work=%d%n",
                    Thread.currentThread().getName(), w);
            Thread.sleep(30);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
