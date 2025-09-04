package com.example.forkJoin;

import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

    private long workload = 0;

    public MyRecursiveAction(long workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
     if(workload>16) {
         System.out.println("Splitting workload "+this.workload);

         long workload1 = this.workload/2;
         long workload2 = this.workload - workload1;

         MyRecursiveAction subtask1 = new MyRecursiveAction(workload1);
         MyRecursiveAction subtask2 = new MyRecursiveAction(workload2);

         subtask1.invoke();
         subtask2.invoke();


     } else {
         System.out.println("Its doing work itself "+this.workload);

     }
    }
}
