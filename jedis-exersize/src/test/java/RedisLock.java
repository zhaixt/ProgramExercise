import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;


/**
 * Created by zhaixiaotong on 2016-6-1.
 */
@Slf4j
public class RedisLock  {



    public static boolean acquireLock(String lock) {
        // 1. 通过SETNX试图获取一个lock
        boolean success = false;
        Jedis jedis =  new Jedis("10.253.11.47",6379);
        long expired = 5000;
        long value = System.currentTimeMillis() +expired + 1;
        System.out.println("set value:"+value);
        long acquired = jedis.setnx(lock, String.valueOf(value));
        System.out.println(acquired);
        //SETNX成功，则成功获取一个锁
        if (acquired == 1) {
            success = true;

            //SETNX失败，说明锁仍然被其他对象保持，检查其是否已经超时
        }else {
            long oldValue = Long.valueOf(jedis.get(lock));
            System.out.println("old value:"+jedis.get(lock));
            //超时
            if (oldValue < System.currentTimeMillis()) {
                System.out.println("now is:"+System.currentTimeMillis());
                String getValue = jedis.getSet(lock, String.valueOf(value));
                // 获取锁成功
                if (Long.valueOf(getValue) == oldValue) {
                    success = true;
                    System.out.println("acquired again success");
                } else {// 已被其他进程捷足先登了
                    success = false;
                    System.out.println("acquired again fail");
                }
            }
            //未超时，则直接返回失败
            else
                success = false;
        }

        return success;
    }

    //释放锁
    public static void releaseLock(String lock) {
        Jedis jedis =  new Jedis("10.253.11.47",6379);
        long current = System.currentTimeMillis();
        // 避免删除非自己获取得到的锁
        System.out.println("release lock:"+jedis.get(lock)+" by lock:"+lock);
        if (current < Long.valueOf(jedis.get(lock)))
            jedis.del(lock);
        jedis.close();
    }

}
