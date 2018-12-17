package distribute;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ZKUtils {
    private static final Logger log = LoggerFactory.getLogger(ZKUtils.class);

    private static String DEFAULT_SEPARATOR = "/";

    private static String ZK_ROOT_PATH      = "/zis/recommend";

    private static String TEMP_LEADER       = "leader-";


    private static boolean         regist            = false;

    private static String LEADER_PATH;



    private static String zkUrl = "10.139.32.187:2181";

    private static ZaCuratorClient zacc;




    public static void createPath(String nodeName,byte[] data) {

            if (!zacc.isExists(makePath(nodeName))) {
                try {
                    zacc.createPath(makePath(nodeName), data);
                } catch (Exception e) {
                    log.error(String.format("create node error, node name is :%s and exceptino is ", nodeName), e);
                }
            }

    }
    public static void createEphemeralPath(String nodeName,byte[] data) {

        if (!zacc.isExists(makePath(nodeName))) {
            try {
                zacc.createEphemeral(makePath(nodeName), data);
            } catch (Exception e) {
                log.error(String.format("create node error, node name is :%s and exceptino is ", nodeName), e);
            }
        }

    }
    public static void init() {
        if (zacc == null) {
            try {
                if (StringUtils.isBlank(zkUrl)) {
                    zacc = new ZaCuratorClient(zkUrl + ZK_ROOT_PATH);
                } else {
                    zacc = new ZaCuratorClient(zkUrl + ZK_ROOT_PATH);
                }
                zacc.start();
                // 初始化节点信息
//                initNode();
            } catch (Exception e) {
                log.error("zk init error. exception:", e);
            }
        }
    }

    public static void setDataToNode(String appName, String data) throws Exception {
        String ephemeralTaskName = makePath(appName);
        zacc.setData(ephemeralTaskName, data.getBytes());
    }



    public static String makePath(String... nodeNames) {
        if (ArrayUtils.isNotEmpty(nodeNames)) {
            StringBuffer sb = new StringBuffer();
            for (String nodeName : nodeNames) {
                sb.append(DEFAULT_SEPARATOR + nodeName);
            }
            return sb.toString();
        }
        return null;
    }

    public static List<String> getNodesChild(String nodeName) {
        String nodes = makePath(nodeName);
        try {
            return zacc.getChildren(nodes);
        } catch (Exception e) {
            log.error("get nodes error : ", e);
        }
        return null;
    }

    public static JSONObject readZKData(String appName) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(appName)) {
            try {
                String configStr = new String(zacc.getData(makePath(appName)));
                return JSONObject.parseObject(configStr);
            } catch (Exception e) {
                log.error("get zk by " + appName + " error.", e);
            }
        }
        return null;
    }
    public static ZaCuratorClient getZacc() {
        return zacc;
    }

}
