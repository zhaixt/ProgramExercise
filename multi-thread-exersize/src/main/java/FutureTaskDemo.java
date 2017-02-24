/**
 * Created by zhaixiaotong on 2016-6-13.
 */

import bean.PrivateAccount;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Administrator
 */
@SuppressWarnings("all")
public class FutureTaskDemo {
    public static void main(String[] args) {
        // 初始化一个Callable对象和FutureTask对象
        Callable pAccount = new PrivateAccount();
        FutureTask futureTask = new FutureTask(pAccount);
        // 使用futureTask创建一个线程
        Thread pAccountThread = new Thread(futureTask);
        long a = System.currentTimeMillis();
        System.out.println("futureTask线程现在开始启动，启动时间为：" +a);
        pAccountThread.start();
        System.out.println("主线程开始执行其他任务");
        // 从其他账户获取总金额
        int totalMoney = new Random().nextInt(100000);
        System.out.println("现在你在其他账户中的总金额为" + totalMoney);
        System.out.println("等待私有账户总金额统计完毕...");
        // 测试后台的计算线程是否完成，如果未完成则等待
        while (!futureTask.isDone()) {
            try {
                Thread.sleep(50);
                System.out.println("私有账户计算未完成继续等待...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long b = System.currentTimeMillis() -a;
        System.out.println("futureTask线程计算完毕，此时时间为" + b);
        Integer privateAccountMoney = null;
        try {
//            futureTask最主要的作用就是获取到子线程的值
            privateAccountMoney = (Integer) futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("您现在的总金额为：" + totalMoney + privateAccountMoney.intValue());



        /*
        * 或者FutureTask采用直接调用new Callable()的方式
        * */
        FutureTask futureTask2 = new FutureTask(new Callable() {
            Integer totalMoney;

            @Override
            public Object call() throws Exception {
                System.out.println("task2 test");
                totalMoney = new Integer(new Random().nextInt(10000));
                System.out.println("您当前有" + totalMoney + "在您的私有账户中");
                return totalMoney.toString();
            }
        });
        new Thread(futureTask2).start();
        String result2 = "";
        if (null != futureTask2) {
            //这里的result拿到futuretask的返回值，如果超过times，则超时
             long c = System.currentTimeMillis();
            System.out.println("begin:" + c);
            try {
                result2 = (String) futureTask2.get(1000, TimeUnit.SECONDS);//aop实现，如果出现异常，reslt这里抛出异常
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } finally {
//                executorService.shutdown();
            }
             long d = System.currentTimeMillis() - c;

            System.out.println("end :" + d);
            System.out.println("result is :" + result2);

        } else {
            System.out.println("result is empty");
        }


    }


//    或者

}

