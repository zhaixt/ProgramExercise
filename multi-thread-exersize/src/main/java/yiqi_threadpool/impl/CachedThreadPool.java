package yiqi_threadpool.impl;

// import com.huifu.mesh.common.threadpool.ThreadPool;
import yiqi_threadpool.ThreadPool;
import yiqi_threadpool.factory.NamedInternalThreadFactory;

import java.util.concurrent.*;

public class CachedThreadPool implements ThreadPool {

    public static Executor getExecutor(String namePrefix, int coreSize, int maxSize, int queueSize, int aliveMillis, boolean isDaemon) {
        return getExecutor(namePrefix, coreSize, maxSize, queueSize, aliveMillis, isDaemon, new ThreadPoolExecutor.AbortPolicy());
    }

    public static Executor getExecutor(String namePrefix, int coreSize, int maxSize, int queueSize, int aliveMillis, boolean isDaemon, RejectedExecutionHandler rejectPolicy) {
        return new ThreadPoolExecutor(coreSize, maxSize, aliveMillis, TimeUnit.MILLISECONDS,
                queueSize == 0 ? new SynchronousQueue<>() :
                        (queueSize < 0 ? new LinkedBlockingQueue<>()
                                : new LinkedBlockingQueue<>(queueSize)),
                new NamedInternalThreadFactory(namePrefix, isDaemon), rejectPolicy);
    }
}
