import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.RetryNTimes;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;


/**
 * Created by zhaixiaotong on 2016-5-24.
 */
@Slf4j
public class CuratorTest {

    public static void main(String[] args) throws Exception {
        String path = "/";
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.18.19.17:12181").retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).connectionTimeoutMs(5000).build();
        // 启动 上面的namespace会作为一个最根的节点在使用时自动创建
        client.start();

        // 创建一个节点
//         client.create().forPath("/test111", "test".getBytes("UTF-8"));
//
//        // 异步地删除一个节点
        //.delete().inBackground().forPath("/test111");
//
//        // 创建一个临时节点
//        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test222", new byte[0]);
//
//        // 取数据
//        byte[] midbytes= client.getData().watched().inBackground().forPath("/test111");
//        byte[] midbytes= client.getData().forPath("/test111");
//        String str2=new String(midbytes,"UTF-8");
//        System.out.println("get data:"+str2);
//
//        // 检查路径是否存在
       Stat x = client.checkExists().forPath("/");
        System.out.println(x.toString());

        ///父目录下有子节点，不能直接删除，会报错： KeeperErrorCode = Directory not empty for /testParentPath
//            client.delete().forPath("/zk");
//        // 异步删除
        //client.delete().inBackground().forPath("/test222");
//
//        // 注册观察者，当节点变动时触发
//        client.getData().usingWatcher(new Watcher() {
//            @Override
//            public void process(WatchedEvent event_listener) {
//                System.out.println("node is changed");
//            }
//        }).inBackground().forPath("/test");

        // 结束使用
        client.close();
    }
}
