import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhaixt
 * @date: 2019/7/2 10:34
 */

public class ProgramThreadFactory implements ThreadFactory, Closeable {
    private String prefix;
    private static ConcurrentMap<String, AtomicInteger> prefixToSeq = new ConcurrentHashMap<String, AtomicInteger>();

    private static final String THREAD_DEFAULT_PREFIX = "program-";
    public static final String THREAD_GROUP_NAME = "program-thread-group";
    private static ThreadGroup threadGroup = new ThreadGroup(THREAD_GROUP_NAME);
    private List<WeakReference<Thread>> threadList = Collections.synchronizedList(new ArrayList<WeakReference<Thread>>());

    public ProgramThreadFactory() {
        this(null);
    }

    public ProgramThreadFactory(String namePrefix) {
        if (namePrefix != null) {
            this.prefix = namePrefix;
        } else {
            this.prefix = THREAD_DEFAULT_PREFIX;
        }
    }
    public static ThreadGroup getThreadGroup() {
        return threadGroup;
    }
    @Override
    public Thread newThread(Runnable r) {
        return newThread(r, "");
    }

    public Thread newThread(Runnable r, String threadNamePrefix) {
        String threadPrefix = prefix + threadNamePrefix;
        prefixToSeq.putIfAbsent(threadPrefix, new AtomicInteger(1));
        Thread thread = new Thread(threadGroup, r, threadPrefix + prefixToSeq.get(threadPrefix).getAndIncrement());
        thread.setDaemon(true);
        threadList.add(new WeakReference<Thread>(thread));
        return thread;
    }
    @Override
    public void close() {
        for (WeakReference<Thread> ref : threadList) {
            closeThread(ref.get());
        }
    }

    public synchronized static void closeAll() {
        Thread[] threads = new Thread[threadGroup.activeCount()];
        int actualCount = threadGroup.enumerate(threads, false);
        for (int i = 0; i < actualCount; i++) {
            closeThread(threads[i]);
        }
    }

    private static void closeThread(Thread thread) {
        if (thread != null && thread.isAlive()) {
            if (thread instanceof Closeable) {
                try {
                    ((Closeable) thread).close();
                } catch (Exception e) {
                    System.out.println("Unexpected exception occured while close pegasus thread."+e.toString());
                }
            }
            thread.interrupt();
        }
    }

}
