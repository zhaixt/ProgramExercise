package distribute;

import com.alibaba.fastjson.JSONObject;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by zhaixiaotong on 2017-6-5.
 * 使用的是apache的curator，不是netflix的
 */

public class ZKTest {
    public static void main(String[] args) {
        String ip = "10.253.11.207";
        int port = 8085;
        String appName = "movies";
        String algorithm = "als";
        String jobId = "ALSExample";
        ZKUtils.init();
        JSONObject jsonParam = new JSONObject();
        String path = appName + "/" + algorithm;
        jsonParam.put("ip", ip);
        jsonParam.put("port", port);
        ZKUtils.createPath(path, algorithm.getBytes());
        String jobPath = path + "/" + jobId;
        ZKUtils.createEphemeralPath(jobPath, jsonParam.toJSONString().getBytes());
        String jobId2 = "ALSExample2";
        String ip2 = "10.253.110.120";
        int port2 = 8080;
        JSONObject jsonParam2 = new JSONObject();
        jsonParam2.put("ip", ip2);
        jsonParam2.put("port", port2);
        String jobPath2 = path + "/" + jobId2;
        ZKUtils.createEphemeralPath(jobPath2, jsonParam2.toJSONString().getBytes());
        List<String> childrenList = ZKUtils.getNodesChild(path);
        List<JSONObject> objectList = new ArrayList<>();
        for (String chiledren : childrenList) {
            objectList.add(ZKUtils.readZKData(path + "/" + chiledren));
        }
        System.out.println(objectList.toString());
        Random random = new Random();

        int index = random.nextInt(objectList.size());
        System.out.println("random index:" + index);
        System.out.println("random result" + objectList.get(index).toString());

        List<String> childrenList2 = ZKUtils.getNodesChild("movies");
        System.out.println("childrenList2" + childrenList2.toString());

//        String result = ZKUtils.readZKData(jobPath).toJSONString();
//        System.out.println("zk data:"+result)"

//        watchZKTreeNode();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----end-----");
        //end 了以后，临时节点会消失。
    }

    private static void watchZKTreeNode() {
        try {
            String path = "/topline_health/test";
            ZKUtils.getZacc().registerTreeWatcher(path, new ZaCuratorClient.ZaTreeWatcher() {
                public void process(TreeCacheEvent.Type type, byte[] data, String childPath) throws Exception {
                    String dataString;
                    if (data != null) {
                        dataString = new String(data);
                    }else{
                        dataString = "";
                    }
                    System.out.println("type:" + type + ",data:" + dataString + ",child path:" + childPath);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("watch zk node error.", e);
        }
    }
}
