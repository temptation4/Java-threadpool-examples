A ThreadPool is a collection of worker threads that are reused to execute multiple tasks. Instead of creating a new thread for every task (which is costly in terms of CPU and memory), a thread pool improves performance by reusing threads.

Java provides thread pools via the Executor Framework (java.util.concurrent.Executors and ThreadPoolExecutor).

**1. Fixed Thread Pool**

A thread pool with a fixed number of threads.

If all threads are busy, new tasks wait in a queue until a thread becomes available.

Good for controlling the number of concurrent threads.

Example:

ExecutorService executor = Executors.newFixedThreadPool(5);

**2. Cached Thread Pool
**
Creates a pool with an unbounded number of threads.

Reuses idle threads if available; otherwise creates new threads.

Suitable for executing many short-lived asynchronous tasks.

Example:

ExecutorService executor = Executors.newCachedThreadPool();

3. **Single Thread Executor**

A pool with only one worker thread.

Ensures tasks are executed sequentially in the order submitted.

Good when tasks must not run concurrently.

Example:

ExecutorService executor = Executors.newSingleThreadExecutor();

4.** Scheduled Thread Pool**

A pool that can schedule tasks to run after a delay or periodically.

Useful for background jobs, cron-like tasks, and maintenance work.

Example:

ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
scheduler.schedule(() -> {
    System.out.println("Task executed after 5 seconds");
}, 5, TimeUnit.SECONDS);

5. **Work-Stealing Pool** (Java 8+)

Uses ForkJoinPool internally.

Threads try to "steal" work from other threads’ queues if they are idle.

Good for parallel processing tasks like recursive algorithms.

Example:

ExecutorService executor = Executors.newWorkStealingPool();

6. **Custom Thread Pool **(ThreadPoolExecutor)

You can create your own thread pool with custom settings.

Useful when you need more control over:

Core pool size

Maximum pool size

Keep-alive time

Task queue type

Example:

ThreadPoolExecutor executor = new ThreadPoolExecutor(
    2,                       // corePoolSize
    4,                       // maximumPoolSize
    10, TimeUnit.SECONDS,    // keepAliveTime
    new LinkedBlockingQueue<>(100) // workQueue
);


✅ Summary

Fixed Thread Pool → Fixed size, reusable threads.

Cached Thread Pool → Grows dynamically, short tasks.

Single Thread Executor → Sequential execution.

Scheduled Thread Pool → Delayed / periodic execution.

Work-Stealing Pool → Parallelism with ForkJoinPool.

Custom Thread Pool → Full control using ThreadPoolExecutor.
