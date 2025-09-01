# Java ThreadPool Examples (Java 19)

This repository demonstrates **different types of thread pools** in Java using the **Executor Framework**, along with examples of creating **custom thread pools**.

## 📌 Prerequisites
- Java 19+
- Maven/Gradle or simple JDK compilation

---

## 🔹 Types of Thread Pools in Java

Java provides factory methods in the `Executors` utility class to create different kinds of thread pools.

### 1. Fixed Thread Pool
A pool with a fixed number of threads.  
- Best for CPU-bound tasks where the number of worker threads is controlled.

```java
ExecutorService executor = Executors.newFixedThreadPool(4);

2. Cached Thread Pool

A pool that creates new threads as needed, but reuses previously created threads when available.

Best for short-lived asynchronous tasks.

ExecutorService executor = Executors.newCachedThreadPool();

3. Single Thread Executor

Executes tasks sequentially on a single worker thread.

Best when tasks must not run in parallel (e.g., logging).

ExecutorService executor = Executors.newSingleThreadExecutor();

4. Scheduled Thread Pool

Executes tasks after a delay or periodically.

Best for scheduled jobs (e.g., background tasks, monitoring).

ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
executor.schedule(() -> {
    System.out.println("Task executed after delay");
}, 5, TimeUnit.SECONDS);

5. Work-Stealing Pool (Java 8+)

Uses a ForkJoinPool under the hood.

Good for parallelism and dividing tasks into subtasks.

ExecutorService executor = Executors.newWorkStealingPool();

🔹 Custom ThreadPoolExecutor

Sometimes, factory methods are not enough, and you need more control (core threads, queue size, rejection policy, etc.).

ExecutorService executor = new ThreadPoolExecutor(
        2,                         // core pool size
        4,                         // maximum pool size
        60, TimeUnit.SECONDS,      // idle thread keep-alive time
        new LinkedBlockingQueue<>(100), // task queue
        Executors.defaultThreadFactory(), 
        new ThreadPoolExecutor.AbortPolicy() // rejection handler
);

📌 Custom Policies

AbortPolicy → throws RejectedExecutionException

CallerRunsPolicy → runs the task in the caller thread

DiscardPolicy → silently discards the task

DiscardOldestPolicy → discards the oldest task in the queue

🚀 Running the Examples

Clone this repo:

git clone git@github.com:Temptation4/Java-threadpool-examples.git
cd Java-threadpool-examples


Compile and run:

javac -source 19 -target 19 src/*.java
java Main

📖 References

Java ExecutorService Documentation

ThreadPoolExecutor Javadoc

👨‍💻 Author: Neelu sahai
