import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaixiaotong on 2016-6-24.
 */
//可以做到，master节点挂了，slave节点以后，程序将自动原slava当成新的master，进行操作
public class Sentinel {
    public static void main(String[] args){
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("10.253.11.47:26379");
        JedisSentinelPool pool = new JedisSentinelPool("master1", sentinels);

        Jedis jedis = pool.getResource();

        String result = jedis.set("jedis", "redis");
        System.out.println(result);
        pool.returnResource(jedis);
    }
}
