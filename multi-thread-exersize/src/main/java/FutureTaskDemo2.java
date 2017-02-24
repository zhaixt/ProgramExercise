import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zhaixiaotong on 2016-7-18.
 */

/*
*   Callable接口类似于Runnable，从名字就可以看出来了，但是Runnable不会返回结果，并且无法抛出返回结果的异常。
*   而Callable功能更强大一些，被线程执行后，可以返回值，这个返回值可以被Future拿到，也就是说，Future可以拿到异步执行任务的返回值，下面来看一个简单的例子：
* */
public class FutureTaskDemo2 {
    public static void main (String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//    ExecutorService executorService2 = Executors.newCachedThreadPool();


//        FutureTask futureTask = (FutureTask) executorService.submit(new Callable<String>() {
//            Integer totalMoney;
//
//            @Override
//            public String call() throws Exception {
//                Thread.sleep(5000);
//                totalMoney = new Integer(new Random().nextInt(10000));
//                System.out.println("您当前有" + totalMoney + "在您的私有账户中");
//                return totalMoney.toString();
//            }
//        });
//
//        String result = "";
//        if (null != futureTask) {
//            //这里的result拿到futuretask的返回值，如果超过times，则超时
//            long a = System.currentTimeMillis();
//            System.out.println("begin:"+a);
//            try {
//                result = (String)futureTask.get(1000, TimeUnit.SECONDS);//aop实现，如果出现异常，reslt这里抛出异常
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }finally{
//                executorService.shutdown();
//            }
//            long b = System.currentTimeMillis()-a;
//
//            System.out.println("end :"+b);
//            System.out.println("result is :"+result);
//
//        }else{
//            System.out.println("result is empty");
//        }

        //在这里可以做别的任何事情

//       String result = futureTask.get(7000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
//
//        executorService.shutdown();



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
            long a = System.currentTimeMillis();
            System.out.println("begin:"+a);
            try {
                result2 = (String)futureTask2.get(1000, TimeUnit.SECONDS);//aop实现，如果出现异常，reslt这里抛出异常
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }finally{
                executorService.shutdown();
            }
            long b = System.currentTimeMillis()-a;

            System.out.println("end :"+b);
            System.out.println("result is :"+result2);

        }else{
            System.out.println("result is empty");
        }
    }

}
