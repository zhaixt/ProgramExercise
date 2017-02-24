import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhaixiaotong on 2016-6-1.
 */
public class ConcurrentTest{
//    @Test
    public static void main(String[] args){
//        System.out.println(Thread.g)
        final Printer outer = new Printer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                outer.output("I am a boy.");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                outer.output("You are a girl.");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                outer.output("She is a gay.");
            }
        }).start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
class Printer {
    Lock lock=new ReentrantLock();
    RedisLock redisLock = new RedisLock();

    public void output(String name) {
//        lock.lock();
//        synchronized (this) {
        if(redisLock.acquireLock("lockt1")) {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            redisLock.releaseLock("lockt1");
        }
//        }
//        lock.unlock();
    }
}
