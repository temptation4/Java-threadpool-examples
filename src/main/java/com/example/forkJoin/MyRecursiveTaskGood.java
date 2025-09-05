package com.example.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask version that RETURNS a result.
 * Still uses fork() + join() to enable parallelism.
 */
public class MyRecursiveTaskGood extends RecursiveTask<Long> {
    private final long workload;

    public MyRecursiveTaskGood(long workload) {
        this.workload = workload;
    }

    @Override
    protected Long compute() {
        if (workload > 16) {
            long w1 = workload / 2;
            long w2 = workload - w1;

            MyRecursiveTaskGood left  = new MyRecursiveTaskGood(w1);
            MyRecursiveTaskGood right = new MyRecursiveTaskGood(w2);

            // fork left asynchronously
            left.fork();

            // compute right directly
            long rightResult = right.compute();

            // wait for left and combine
            long leftResult = left.join();

            return leftResult + rightResult;
        } else {
            try {
                System.out.printf("[TASK] Thread=%s computing=%d%n",
                        Thread.currentThread().getName(), workload);
                Thread.sleep(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return workload * 3;
        }
    }
}
