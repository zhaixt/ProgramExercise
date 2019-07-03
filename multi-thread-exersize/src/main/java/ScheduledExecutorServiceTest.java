import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhaixt
 * @date: 2019/7/2 10:28
 */
public class ScheduledExecutorServiceTest {
    public static final String THREAD_SCHEDULED_TEST = "thread_scheduled_test-";

    public static void main(String[] args) {
        //todo 只有scheduled的时候，加上ProgramThreadFactory会直接退出。。。
        ScheduledExecutorService scheduled = new ScheduledThreadPoolExecutor(2, new ProgramThreadFactory(THREAD_SCHEDULED_TEST));
        //0表示首次执行任务的延迟时间，1000表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
        scheduled.scheduleAtFixedRate(() ->
                System.out.println(Thread.currentThread().getName()+"，time: " + System.currentTimeMillis()), 0, 1000, TimeUnit.MILLISECONDS);
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);
        executorService.scheduleAtFixedRate(() ->
                System.out.println("run: " + System.currentTimeMillis()), 0, 1000, TimeUnit.MILLISECONDS);

    }

}
