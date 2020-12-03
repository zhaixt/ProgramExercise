package yiqi_threadpool.impl.eager;

import yiqi_threadpool.ThreadPool;
import yiqi_threadpool.factory.NamedInternalThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池特性：
 * 当核心线程不够用时，会首先创建新的线程直到线程数达到最大线程数，而不是优先将任务放在等待队列中。
 */
public class EagerThreadPool implements ThreadPool {

    public static Executor getExecutor(String namePrefix, int coreSize, int maxSize, boolean isDaemon) {
        return getExecutor(namePrefix, coreSize, maxSize, DEFAULT_QUEUES_SIZE, DEFAULT_ALIVE_MILLIS, isDaemon);
    }

    public static Executor getExecutor(String namePrefix, int coreSize, int maxSize, int queueSize, int aliveMillis, boolean isDaemon) {
        return getExecutor(namePrefix, coreSize, maxSize, queueSize, aliveMillis, isDaemon, new ThreadPoolExecutor.AbortPolicy());
    }

    public static Executor getExecutor(String namePrefix, int coreSize, int maxSize, int queueSize, int aliveMillis, boolean isDaemon, RejectedExecutionHandler rejectPolicy) {
        TaskQueue<Runnable> taskQueue = new TaskQueue<Runnable>(queueSize <= 0 ? 1 : queueSize);
        EagerThreadPoolExecutor executor = new EagerThreadPoolExecutor(coreSize,
                maxSize,
                aliveMillis,
                TimeUnit.MILLISECONDS,
                taskQueue,
                new NamedInternalThreadFactory(namePrefix, isDaemon),
                rejectPolicy
                );
        taskQueue.setExecutor(executor);
        return executor;
    }
}
