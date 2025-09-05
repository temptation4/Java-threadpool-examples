package com.example.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import com.example.forkjoin.MyRecursiveActionBad;

public class Main {

    public static void main(String[] args) {
        int parallelism = Math.max(2, Runtime.getRuntime().availableProcessors());
        ForkJoinPool pool = new ForkJoinPool(parallelism);

        System.out.println("ForkJoinPool parallelism: " + pool.getParallelism());

        long workload = 123;

        // Run bad example
        System.out.println("\n=== BAD RecursiveAction (invoke() inside compute) ===");
        long t1 = System.currentTimeMillis();
        pool.invoke(new MyRecursiveActionBad(workload));
        long t2 = System.currentTimeMillis();
        System.out.println("BAD elapsed ms: " + (t2 - t1));

        // Run good example
        System.out.println("\n=== GOOD RecursiveAction (fork/join) ===");
        long t3 = System.currentTimeMillis();
        pool.invoke(new MyRecursiveActionGood(workload));
        long t4 = System.currentTimeMillis();
        System.out.println("GOOD elapsed ms: " + (t4 - t3));

        // Run good RecursiveTask
        System.out.println("\n=== GOOD RecursiveTask (fork/join with result) ===");
        long t5 = System.currentTimeMillis();
        ForkJoinTask<Long> future = pool.submit(new MyRecursiveTaskGood(workload));
        long result = future.join();
        long t6 = System.currentTimeMillis();
        System.out.println("TASK result: " + result + " | elapsed ms: " + (t6 - t5));

        pool.shutdown();
    }

}
