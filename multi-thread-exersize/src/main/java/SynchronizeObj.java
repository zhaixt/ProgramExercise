/**
 * Created by zhaixt on 2017/10/21.
 * 如果A加thread.sleep，A和C先出，B5s后出，代表加载方法上作用范围大
 * 如果在B上加thread.sleep，A、B、C同时出现
 */
public class SynchronizeObj {
    public synchronized void methodA() {
        System.out.println("methodA.....");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void methodB() {
        synchronized(this) {
            System.out.println("methodB.....");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void methodC() {
        String str = "sss";
        synchronized (str) {
            System.out.println("methodC.....");
        }
    }
}
