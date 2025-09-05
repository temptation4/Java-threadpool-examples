package com.example.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * BAD EXAMPLE:
 * This version calls invoke() inside compute().
 * invoke() is blocking -> it runs the subtask immediately and waits for it.
 * That means subtasks run one after the other, SERIALIZED, no parallelism.
 * So even though we're inside ForkJoinPool, we lose the benefit of parallel execution.
 */
public class MyRecursiveActionBad extends RecursiveAction {
    private final long workload;

    public MyRecursiveActionBad(long workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload > 16) {
            long w1 = workload / 2;
            long w2 = workload - w1;

            MyRecursiveActionBad t1 = new MyRecursiveActionBad(w1);
            MyRecursiveActionBad t2 = new MyRecursiveActionBad(w2);

            // ❌ BAD: invoke() blocks the current thread until task finishes.
            // So first t1 runs, completes fully, then t2 runs.
            // No other thread can "steal" these tasks, so it's just serial recursion.
            t1.invoke();
            t2.invoke();
        } else {
            doWork(workload);
        }
    }

    private void doWork(long w) {
        try {
            System.out.printf("[BAD] Thread=%s doing work=%d%n",
                    Thread.currentThread().getName(), w);
            Thread.sleep(30); // simulate some real work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
