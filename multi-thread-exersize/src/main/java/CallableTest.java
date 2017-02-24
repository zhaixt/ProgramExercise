import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by zhaixiaotong on 2017-2-14.
 */
public class CallableTest {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        try {
            Thread.sleep(5000);// 可能做一些事情

            Integer a = callable.call();
            System.out.println("call result is："+a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {

            Thread.sleep(5000);// 可能做一些事情
            System.out.println("future result is:"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
