import com.sun.istack.internal.logging.Logger;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/**
 * Created by zhaixiaotong on 2016-5-16.
 */
//@Slf4j
//public class RedisLock implements Lock {
//    @Autowired
//    protected StringRedisTemplate redisTemplate;
//
//    private static final Logger logger = Logger.getLogger(RedisLock.class);
//
//    // lock flag stored in redis
//    private static final String LOCKED = "TRUE";
//
//    // timeout(ms)
//    private static final long TIME_OUT = 30000;
//
//    // lock expire time(s)
//    public static final int EXPIRE = 60;
//
//    // private Jedis jedis;
//    private String key;
//
//    // state flag
//    private volatile boolean locked = false;
//
//    private static ConcurrentMap<String, RedisLock> map = Maps.newConcurrentMap();
//
//    public RedisLock(String key) {
//        this.key = "_LOCK_" + key;
//        redisTemplate = (StringRedisTemplate) ApplicationContextHolder.getBean("redisTemplate");
//    }
//
//    public static RedisLock getInstance(String key) {
//        return map.getOrDefault(key, new RedisLock(key));
//    }
//
//    public void lock(long timeout) {
//        long nano = System.nanoTime();
//        timeout *= 1000000;
//        final Random r = new Random();
//        try {
//            while ((System.nanoTime() - nano) < timeout) {
//                if (redisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), LOCKED.getBytes())) {
//                    redisTemplate.expire(key, EXPIRE, TimeUnit.SECONDS);
//                    locked = true;
//                    logger.debug("add RedisLock[" + key + "].");
//                    break;
//                }
//                Thread.sleep(3, r.nextInt(500));
//            }
//        } catch (Exception e) {
//        }
//    }
//
//    @Override
//    public void unlock() {
//        if (locked) {
//            logger.debug("release RedisLock[" + key + "].");
//            redisTemplate.delete(key);
//        }
//    }
//
//    @Override
//    public void lock() {
//        lock(TIME_OUT);
//    }
//
//    @Override
//    public void lockInterruptibly() throws InterruptedException {
//
//    }
//
//    @Override
//    public Condition newCondition() {
//        return null;
//    }
//
//    @Override
//    public boolean tryLock() {
//        return false;
//    }
//
//    @Override
//    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
//        return false;
//    }
//}
