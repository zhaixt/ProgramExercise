/**
 * Created by zhaixt on 2015/9/28.
 */


public class MyThread extends Thread {
    private int count = 5;
//    Constant cst = new Constant();
    public MyThread(String name){
        super();
        this.setName(name);//设置线程名称
    }
    @Override
    public void run(){
        super.run();
        count--;
        System.out.println("由"+this.currentThread().getName()+"计算，count="+count);
//        Constant.num -- ;
//       System.out.println("由"+this.currentThread().getName()+"计算， Constant.num="+count);

    }
}
