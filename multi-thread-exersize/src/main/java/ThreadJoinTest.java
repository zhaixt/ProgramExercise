/**
 * https://blog.csdn.net/u013425438/article/details/80205693
 */
public class ThreadJoinTest {
    /*主线程在t1.join()方法处停止，并需要等待A线程执行完毕后才会执行t3.start()，
    然而，并不影响B线程的执行。因此，可以得出结论，t.join()方法只会使主线程进入等待池并等待t线程执行完毕后才会被唤醒。
    并不影响同一时刻处在运行状态的其他线程。*/
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadTest t1 = new ThreadTest("A");
        ThreadTest t2 = new ThreadTest("B");
        ThreadTest t3 = new ThreadTest("C");
        System.out.println("t1start");
        t1.start();
        System.out.println("t1end");
        System.out.println("t2start");
        t2.start();
        System.out.println("t2end");
        /*只会停止主线程，B线程不妨碍，A线程走完了主线程就可以继续了*/
        t1.join();
        System.out.println("t3start");
        t3.start();
        System.out.println("t3end");
        System.out.println(Thread.currentThread().getName() + " end");

    }

}

class ThreadTest extends Thread {
    private String name;

    public ThreadTest(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + "-" + i);
        }
    }
}