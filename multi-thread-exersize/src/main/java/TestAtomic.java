/**
 * Created by zhaixt on 2015/12/9.
 */


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试原子性的同步
 * @author polarbear 2009-3-14
 *
 */
public class TestAtomic {

    public static AtomicInteger astom_i = new AtomicInteger();

    public static volatile Integer v_integer_i = 0;

    public static volatile int v_i = 0;

    public static Integer integer_i = 0;

    public static int i = 0;

    public static int endThread = 0;

    public static void main(String[] args) {
        new TestAtomic().testAtomic();
    }

    public void testAtomic() {

        for(int i=0; i<100; i++) {
            new Thread(new IntegerTestThread()).start();
        }

        try {
            for(;;) {
                Thread.sleep(500);
                if(TestAtomic.endThread == 100) {
                    System.out.println(">>Execute End:");
                    System.out.println(">>Atomic: \t"+ TestAtomic.astom_i);
                    System.out.println(">>VInteger: \t"+ TestAtomic.v_integer_i);
                    System.out.println(">>Integer: \t"+ TestAtomic.integer_i);
                    System.out.println(">>Source i: \t"+ TestAtomic.i);
                    System.out.println(">>Source Vi: \t"+ TestAtomic.v_i);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class IntegerTestThread implements Runnable {
    public void run() {
        int x = 0;
        while(x<1000) {
            TestAtomic.astom_i.incrementAndGet();
            TestAtomic.v_integer_i++;
            TestAtomic.integer_i++;
            TestAtomic.i++;
            TestAtomic.v_i++;
            x++;
        }
        ++TestAtomic.endThread;
    }
}
