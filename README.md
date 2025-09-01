# Java ThreadPool Examples

This repository contains examples of using **Thread Pools** in Java with the `ExecutorService` framework.

---

## 📌 Introduction

In Java, a **Thread Pool** is a pool (collection) of pre-initialized worker threads that are ready to perform tasks.  
Instead of creating a new thread every time a task is submitted, tasks are queued and executed by one of the existing threads.

This improves performance by:

- Reducing overhead of thread creation/destruction.  
- Managing concurrency efficiently.  
- Allowing reuse of threads.  

Thread pools in Java are managed by the **Executor Framework** (introduced in Java 5).

---

## ⚙️ How Thread Pool Works
1. A task (`Runnable` or `Callable`) is submitted to the Executor.  
2. The Executor picks an available thread from the pool.  
3. If no thread is available, the task waits in a queue.  
4. Once a thread completes a task, it becomes available for the next one.  

---

## 🛠️ Built-in ThreadPool Types (via `Executors` class)

### 1. `newFixedThreadPool(int n)`
- Creates a pool with a fixed number of threads.  
- Useful when the number of threads is known and constant.  
```java
ExecutorService fixedPool = Executors.newFixedThreadPool(5);
2. newCachedThreadPool()
Creates a pool with threads created on demand.

Idle threads are reused, but terminated if unused for 60 seconds.

Suitable for short-lived asynchronous tasks.

java
Copy code
ExecutorService cachedPool = Executors.newCachedThreadPool();
3. newSingleThreadExecutor()
A single worker thread executes tasks sequentially.

Ensures tasks are executed one by one in order.

java
Copy code
ExecutorService singleThread = Executors.newSingleThreadExecutor();
4. newScheduledThreadPool(int n)
Used for scheduling tasks with delays or periodically.

java
Copy code
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
scheduler.schedule(() -> System.out.println("Task after 5 sec"), 5, TimeUnit.SECONDS);
🏗️ Creating a Custom Thread Pool
Java provides ThreadPoolExecutor for fine-grained control.

Example: Custom ThreadPool
java
Copy code
import java.util.concurrent.*;

public class CustomThreadPoolExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                     // corePoolSize
                4,                     // maximumPoolSize
                10, TimeUnit.SECONDS,  // keepAliveTime
                new ArrayBlockingQueue<>(2), // task queue
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy() // Rejection policy
        );

        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing Task " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            });
        }

        executor.shutdown();
    }
}
🔍 Explanation of Parameters:
corePoolSize (2): Minimum number of threads kept alive.

maximumPoolSize (4): Maximum threads allowed.

keepAliveTime (10s): Extra threads (beyond core) live only for this duration when idle.

workQueue (ArrayBlockingQueue): Queue that holds tasks before execution.

ThreadFactory: Defines how new threads are created.

RejectionPolicy: What happens when the queue is full.

🚦 Rejection Policies in ThreadPoolExecutor
When the task queue is full and no threads are available, the rejection policy decides what happens:

AbortPolicy (default): Throws RejectedExecutionException.

CallerRunsPolicy: Executes task in the caller’s thread.

DiscardPolicy: Silently discards the task.

DiscardOldestPolicy: Discards the oldest unhandled task.

✅ Real-World Use Cases
Handling web server requests (Tomcat, Jetty).

Processing messages from Kafka / RabbitMQ.

Asynchronous background tasks (emails, notifications).

Parallel batch processing (ETL, analytics).

🚀 Getting Started
Requirements
Java 19


✨ Author
 Neelu Sahai
