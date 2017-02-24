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

//        MyThre
// ad a = new
// o MyThread("A");
//        MyThread b = new MyThread("B");
//        MyThread c = new MyThread("C");
//        MyThread d = new MyThread("D");
//        MyThread e = new MyThread("E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

       //线程的另一种写法
        Thread t = new Thread(new Runnable() {
            int i = 0;
            public void run() {
                System.out.println("i="+i);
                while (i<5) {
                    try {
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

//        或者
        new Thread(new Runnable() {
            int j = 0;

            public void run() {
                System.out.println("j="+j);

                while (j<5) {
                    try {
                        j++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
