package queue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaixt
 * @date 2020/3/5 14:45
 */
public class MyDelayQueueTest {
    // DelayQueue<DelayEvent> delayQueue = new DelayQueue<DelayEvent>();
    private static ExecutorService executorService = new ThreadPoolExecutor(2, 2 * 2,
            0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws Exception {
        DelayQueue<DelayEvent> queue = new DelayQueue<DelayEvent>();
        Date now = new Date();
        Date time1 = new Date();
        Date time2 = new Date();
        Date time3 = new Date();
        Date time4 = new Date();
        time1.setTime(now.getTime() + 2 * 1000);
        time2.setTime(now.getTime() + 4 * 1000);
        time3.setTime(now.getTime() - 2 * 1000);
        time4.setTime(now.getTime() - 4 * 1000);
        DelayEvent delayEvent = new DelayEvent(now, "now");
        DelayEvent delayEvent1 = new DelayEvent(time1, "after1");
        DelayEvent delayEvent2 = new DelayEvent(time2, "after2");
        DelayEvent delayEvent3 = new DelayEvent(time3, "before1");
        DelayEvent delayEvent4 = new DelayEvent(time4, "before2");
        queue.offer(delayEvent);
        queue.offer(delayEvent1);
        queue.offer(delayEvent2);
        queue.offer(delayEvent3);
        queue.offer(delayEvent4);
        List<DelayEvent> delayEventList = new ArrayList<>();
        queue.drainTo(delayEventList);
        for (DelayEvent event : delayEventList) {
            System.out.println("event:" + event.name + ", date:" + event.startDate);
        }

    }
}
