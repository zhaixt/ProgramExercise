import java.util.concurrent.*;

/**
 * Created by zhaixt on 2015/11/5.
 */
public class ThreadPoolTest {
    public static void main(String[] args){

        ExecutorService executorService =  Executors.newCachedThreadPool();
//        executorService.submit()
        // ThreadPoolExecutor是ExecutorService的一个实现类，它使用可能的几个池线程之一执行每个提交的任务，通常使用 Executors 工厂方法配置。
        //注意这里的最后一个单数ArrayBlockingQueue不能是无界队列，要不然过了coreSize以后会一直往队列里填写
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,200,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        for(int i=0;i<15;i++){

            MyThreadRunnable myThread=new MyThreadRunnable(i);
//            new Thread(myThread).start();
            threadPoolExecutor.execute(myThread);//通过这个语句执行线程
            System.out.println("线程池中线程数目："+threadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    threadPoolExecutor.getQueue().size()+"，已执行玩别的任务数目："+threadPoolExecutor.getCompletedTaskCount());

        }
        threadPoolExecutor.shutdown();
    }
//    public static ExecutorService newCachedThreadPool(){
//        return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
//    }
}
