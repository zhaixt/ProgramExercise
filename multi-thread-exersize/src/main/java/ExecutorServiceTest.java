import java.util.concurrent.*;

/**
 * Created by zhaixt on 2015/11/5.
 */
public class ExecutorServiceTest {
    public static void main(String[] args){
        ExecutorService executorService =  Executors.newCachedThreadPool();
//        executorService.submit()
        for(int i=0;i<15;i++){

            MyThreadRunnable myThread=new MyThreadRunnable(i);
            executorService.execute(myThread);//通过这个语句执行线程

            System.out.println("current thread is:"+Thread.currentThread());

        }

        System.out.println("做我自己的事情");

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("====executor service run by submit!====");
            }
        });
        executorService.shutdown();
    }
//    public static ExecutorService newCachedThreadPool(){
//        return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
//    }
}
