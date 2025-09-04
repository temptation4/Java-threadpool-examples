package com.example.forkJoin;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {
    
    private long workload = 0;

    public MyRecursiveTask(long workload) {
        this.workload = workload;
    }

    @Override
    protected Long compute() {
        if(workload>16) {
            System.out.println("Splitting workload "+this.workload);

            long workload1 = this.workload/2;
            long workload2 = this.workload - workload1;

            MyRecursiveTask subtask1 = new MyRecursiveTask(workload1);
            MyRecursiveTask subtask2 = new MyRecursiveTask(workload2);

            subtask1.invoke();
            subtask2.invoke();

            long result = 0;
            result+=subtask1.join();
            result+=subtask2.join();
            return result;

        } else {
            System.out.println("Its doing work itself "+this.workload);
            return workload*3;
        }
    }
}
