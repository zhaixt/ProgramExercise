package queue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @author zhaixt
 * @date 2020/3/5 14:26
 */
public class DelayTask implements Runnable {

    private int id;
    private DelayQueue<DelayEvent> queue;

    public DelayTask(int id, DelayQueue<DelayEvent> queue) {
        super();
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + id * 1000);
        System.out.println("Thread " + id + " " + delay);
        for (int i = 0; i < 100; i++) {
            String name = String.valueOf(id) + "_" + String.valueOf(i);
            DelayEvent delayEvent = new DelayEvent(delay, name);
            queue.add(delayEvent);
        }
    }
}
