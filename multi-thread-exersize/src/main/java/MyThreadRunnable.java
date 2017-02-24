/**
 * Created by zhaixt on 2015/11/5.
 */
public class MyThreadRunnable implements Runnable{
    private int taskNum;
    public MyThreadRunnable(int num){
        this.taskNum = num;
    }
    @Override
    public void run(){
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
