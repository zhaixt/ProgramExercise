/**
 * Created by zhaixt on 2015/9/28.
 */
public class RunTest1 {

    public static void main(String[] args) {
        MyThread mythread = new MyThread("test");
        Thread a = new Thread(mythread, "A");
        Thread b = new Thread(mythread, "B");
        Thread c = new Thread(mythread, "C");
        Thread d = new Thread(mythread, "D");
        Thread e = new Thread(mythread, "E");



        a.start();
//        b.start();
//        c.start();
//        d.start();
//        e.start();

        mythread.start();

        //runnable
        Thread m = new Thread(new MyThreadRunnable(2));
        m.start();




       //线程的另一种写法
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("new runnable");
            }
        });
        t.start();

//        或者
        new Thread(new Runnable() {
            int j = 0;

            public void run() {
                System.out.println("new thread,new runnable");
            }
        }).start();
    }
}
