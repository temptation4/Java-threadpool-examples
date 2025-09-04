package com.example.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MyRecursiveAction actions = new MyRecursiveAction(123);
        forkJoinPool.invoke(actions);


        MyRecursiveTask task = new MyRecursiveTask(123);
        Long result = forkJoinPool.invoke(task);
        //System.out.println("result "+result);
        sleep(1000);

        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(task);
       long res = forkJoinTask.get();
        System.out.println("result "+res);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
