package com.example.forkJoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(1, 1000);
        long result = pool.invoke(task);

        System.out.println("Sum from 1 to 1000 = " + result);
    }
}