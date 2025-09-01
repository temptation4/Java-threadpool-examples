Java Thread Pools

Thread pools in Java are part of the Executor Framework (introduced in Java 5). They provide a way to manage a pool of worker threads, reducing the overhead of thread creation and improving performance in concurrent applications.

📌 Built-in Thread Pools (via Executors)
1. FixedThreadPool

A pool with a fixed number of threads.

If all threads are busy, new tasks wait in a queue.

ExecutorService fixedPool = Executors.newFixedThreadPool(4);


✅ Use case: When you know the exact number of threads needed (e.g., handling a set of parallel tasks).

2. CachedThreadPool

Creates new threads as needed, reuses previously constructed ones when available.

Idle threads are terminated after 60 seconds.

ExecutorService cachedPool = Executors.newCachedThreadPool();


✅ Use case: Many short-lived asynchronous tasks.

3. SingleThreadExecutor

Executes tasks sequentially on a single worker thread.

Guarantees FIFO task order.

ExecutorService singleThread = Executors.newSingleThreadExecutor();


✅ Use case: When tasks must be executed in order (logging, file writing).

4. ScheduledThreadPool

Executes tasks after a delay or periodically.

ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

scheduler.schedule(() -> {
    System.out.println("Task executed after 2 seconds");
}, 2, TimeUnit.SECONDS);

scheduler.scheduleAtFixedRate(() -> {
    System.out.println("Task executed every 5 seconds");
}, 1, 5, TimeUnit.SECONDS);


✅ Use case: Timers, periodic jobs, background tasks.

5. WorkStealingPool (Java 8+)

Uses ForkJoinPool internally.

Creates threads equal to the number of available processors.

Efficient for divide-and-conquer parallelism.

ExecutorService workStealing = Executors.newWorkStealingPool();


✅ Use case: Parallel stream processing, recursive tasks.

⚙️ Custom Thread Pool (via ThreadPoolExecutor)

Java provides flexibility to create your own thread pool with fine-grained control.

ExecutorService customPool = new ThreadPoolExecutor(
        2,                 // core pool size
        4,                 // maximum pool size
        30, TimeUnit.SECONDS, // idle thread keep-alive time
        new ArrayBlockingQueue<>(10), // task queue
        Executors.defaultThreadFactory(), // thread factory
        new ThreadPoolExecutor.CallerRunsPolicy() // rejection handler
);

Parameters Explained:

Core Pool Size → Minimum number of threads always alive.

Maximum Pool Size → Maximum number of threads.

Keep Alive Time → Time to keep extra threads alive when idle.

Work Queue → Where tasks wait if all threads are busy.

Thread Factory → Defines how new threads are created.

Rejection Policy → Action when the queue is full:

AbortPolicy (default) → throws RejectedExecutionException

CallerRunsPolicy → runs task in the caller’s thread

DiscardPolicy → silently drops task

DiscardOldestPolicy → drops oldest unhandled task

✅ Use case: When you need full control over concurrency (e.g., high-performance servers, background workers).

👨‍💻 Author: Neelu Sahai
