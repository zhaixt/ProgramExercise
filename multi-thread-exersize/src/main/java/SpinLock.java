import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zhaixt on 2015/12/10.
 */
//自旋锁
public class SpinLock {
    private AtomicReference<Thread>  sign = new AtomicReference<>();
    public void lock(){
        Thread current = Thread.currentThread();
        while(!sign.compareAndSet(null,current)){

        }
    }
    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(null,current);
    }
}
