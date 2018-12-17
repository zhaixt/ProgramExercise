/**
 * Created by zhaixiaotong on 2017-5-26.
 */
class Print

{
    int i=0;
    boolean flag=false;
    public  void print1()
    {
        synchronized(this)
        {
            if(flag==false)
            {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(i<100)
            {
                System.out.println(Thread.currentThread().getName() +" - "+i);
                i++;
            }

            flag=false;
            notify();
        }
    }
    public  void print2()
    {
        synchronized(this)
        {
            if(flag==true)
            {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        if(i<100)
        {
            System.out.println(Thread.currentThread().getName() +" - "+i);
            i++;
        }
            flag=true;
            notify();
        }
    }
}
class MyThread1 extends Thread
{
    public Print print =null;
    public MyThread1(Print print) {
        this.print =print;
    }
    @Override
    public void run()
    {
        while(print.i<100)
            print.print2();
    }
}
class MyThread2 extends Thread
{
    public Print print =null;
    public MyThread2(Print in) {
        this.print =in;
    }
    @Override
    public void run()
    {
        while(print.i<100)
            print.print1();
    }
}
public class Main {
    public static void main(String[] args) {
        //两个线程    交替打印字符串
        Print print =new Print();
        MyThread1 mthread1=new MyThread1(print);
        MyThread2 mthread2=new MyThread2(print);
        new Thread(mthread1).start();
        new Thread(mthread2).start();
    }

}
