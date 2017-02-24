import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by zhaixiaotong on 2016-7-8.
 */
public class JedisJunitTest {

    @Test
    public void setnxTest(){
        Jedis jedis =  new Jedis("10.139.54.36",6390);
        Long a = jedis.setnx("test1","test");
        System.out.println(a);//1成功
        jedis.expire("test1", 4);
        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Boolean c = jedis.exists("test1");
        System.out.println(c);//1存在

//        jedis.del("test1");
        Long b = jedis.setnx("test1","test");
        jedis.del("test1");

        System.out.println(b);


    }
}
