import java.util.concurrent.*;

/**
 * Created by zhaixt on 2015/11/5.
 */
public class ThreadPoolTest {
    public static void main(String[] args){


//        executorService.submit()
        // ThreadPoolExecutor是ExecutorService的一个实现类，它使用可能的几个池线程之一执行每个提交的任务，通常使用 Executors 工厂方法配置。
        //注意这里的最后一个单数ArrayBlockingQueue不能是无界队列，要不然过了coreSize以后会一直往队列里填写
        //corePoolSize和maximumPoolSize设置为同样大，到达coreSize以后，还是会往队列里进，不用担心
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,200,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(100));
        for(int i=0;i<15;i++){

            MyThreadRunnable myThread=new MyThreadRunnable(i);
//            new Thread(myThread).start();
            threadPoolExecutor.execute(myThread);//通过这个语句执行线程
            System.out.println("线程池中线程数目："+threadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    threadPoolExecutor.getQueue().size()+"，已执行玩别的任务数目："+threadPoolExecutor.getCompletedTaskCount());
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("haha");
                }
            });

            threadPoolExecutor.execute(() -> System.out.println("haha"));//通过这个语句执行线程
        }
        threadPoolExecutor.shutdown();
        ExecutorService executorService =  new ThreadPoolExecutor(5,10,200,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(3));
        for(int j=0;j<15;j++){
            MyThreadRunnable myThread=new MyThreadRunnable(j);
            try {
                executorService.submit(myThread);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//    public static ExecutorService newCachedThreadPool(){
//        return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
//    }
}
