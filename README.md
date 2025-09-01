
ExecutorService fixedPool = Executors.newFixedThreadPool(5);
2. CachedThreadPool
Creates new threads as needed, but reuses previously created threads when available.

Suitable for many short-lived asynchronous tasks.


ExecutorService cachedPool = Executors.newCachedThreadPool();
3. SingleThreadExecutor
A single worker thread that executes tasks sequentially.

Ensures tasks are executed in the order they are submitted.


ExecutorService singleThread = Executors.newSingleThreadExecutor();
4. ScheduledThreadPool
Supports delayed and periodic task execution.

ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
scheduler.schedule(() -> {
    System.out.println("Task executed after delay");
}, 3, TimeUnit.SECONDS);
5. WorkStealingPool (Java 8+)
Uses ForkJoinPool internally.

Automatically balances tasks across worker threads.


ExecutorService workStealingPool = Executors.newWorkStealingPool();
6. Custom ThreadPoolExecutor
Full control over:

Core pool size

Maximum pool size

Keep-alive time

Work queue

Rejection policies

java
Copy code
ExecutorService customPool = new ThreadPoolExecutor(
    2,                   // Core pool size
    5,                   // Max pool size
    10, TimeUnit.SECONDS, // Keep alive time
    new LinkedBlockingQueue<>(100), // Work queue
    Executors.defaultThreadFactory(),
    new ThreadPoolExecutor.AbortPolicy() // Rejection policy
);



📝 Requirements
Java 19

Any IDE (IntelliJ / Eclipse) or terminal to run.

📌 Key Points
Use FixedThreadPool for known, fixed workloads.

Use CachedThreadPool for many short-lived async tasks.

Use SingleThreadExecutor when task order matters.

Use ScheduledThreadPool for periodic jobs.

Use WorkStealingPool for parallel tasks.

Use Custom ThreadPoolExecutor when fine-grained control is needed.

👨‍💻 Author: Neelu Sahai
