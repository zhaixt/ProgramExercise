package yiqi_threadpool.impl;


import yiqi_threadpool.ThreadPool;
import yiqi_threadpool.factory.NamedInternalThreadFactory;

import java.util.concurrent.*;

public class FixedThreadPool implements ThreadPool {

    public static ThreadPoolExecutor getExecutor(String namePrefix, int coreSize) {
        return getExecutor(namePrefix, coreSize, 0, false, new ThreadPoolExecutor.AbortPolicy());
    }

    public static ThreadPoolExecutor getExecutor(String namePrefix, int coreSize, int queueSize, boolean isDaemon) {
        return getExecutor(namePrefix, coreSize, queueSize, isDaemon, new ThreadPoolExecutor.AbortPolicy());
    }

    public static ThreadPoolExecutor getExecutor(String namePrefix, int coreSize, int queueSize, boolean isDaemon, RejectedExecutionHandler rejectPolicy) {
        return new ThreadPoolExecutor(coreSize, coreSize, Integer.MAX_VALUE, TimeUnit.MILLISECONDS,
                queueSize == 0 ? new SynchronousQueue<>() :
                        (queueSize < 0 ? new LinkedBlockingQueue<>()
                                : new LinkedBlockingQueue<>(queueSize)),
                new NamedInternalThreadFactory(namePrefix, isDaemon), rejectPolicy);
    }
}
