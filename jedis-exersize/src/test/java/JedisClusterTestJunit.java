import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.util.JedisClusterCRC16;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by zhaixiaotong on 2016-5-16.
 */
public class JedisClusterTestJunit {
    @Test
    public  void cluster(){
        String key = "test";
        // 这东西 可以直接看到key 的分片数，就能知道放哪个 节点
        System.out.println(JedisClusterCRC16.getSlot(key));
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("10.253.11.47", 6379));
//        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7001));
//        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7002));
        // 3个master 节点
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        System.out.println(jc.get(key));
        jc.setnx(key, "test");
        String value = jc.get(key);
        jc.del(key);
        System.out.println(value);
    }

}
