import java.util.Date;

/**
 * Created by zhaixiaotong on 2016-5-24.
 */
public class WaitNotifyTest {
    public static Object object = new Object();
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println("线程1"+Thread.currentThread().getName()+"准备阻塞"+System.currentTimeMillis());
                    object.wait();
                } catch (InterruptedException e) {
                }
                System.out.println("线程1"+Thread.currentThread().getName()+"获取到了锁"+System.currentTimeMillis());
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            System.out.println("进入线程2"+Thread.currentThread().getName()+"时间："+System.currentTimeMillis());

            synchronized (object) {
                System.out.println("线程2"+Thread.currentThread().getName()+"准备调用object.notify()"+System.currentTimeMillis());

                object.notify();
                System.out.println("线程2"+Thread.currentThread().getName()+"调用了object.notify()"+System.currentTimeMillis());
            }
            System.out.println("线程2"+Thread.currentThread().getName()+"释放了锁"+System.currentTimeMillis());
        }
    }

}
