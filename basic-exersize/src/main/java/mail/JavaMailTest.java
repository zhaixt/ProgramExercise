package mail;

/**
 * Created by zhaixt on 2018/2/24.
 */
public class JavaMailTest {
    public static void main(String[] args){
//        ExecutorService executorService =  Executors.newCachedThreadPool();//这里return的就是ThreadPoolExecutor
////        executorService.submit()
//        for(int i=0;i<15;i++){
//
//            MyThreadRunnable myThread=new MyThreadRunnable(i);
//            executorService.execute(myThread);//通过这个语句执行线程
//
//            System.out.println("current thread is:"+Thread.currentThread());
//
//        }
//
//        System.out.println("做我自己的事情");
//
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("====executor service run by submit!====");
//            }
//        });
//        executorService.shutdown();

        Mailer mailer = new Mailer();
        try {
            mailer.send();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
