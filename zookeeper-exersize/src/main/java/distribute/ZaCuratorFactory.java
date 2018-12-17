package distribute;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.util.ArrayList;
import java.util.List;

public class ZaCuratorFactory {

    public static CuratorFramework createClient(String connectionString) {

        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(3000, 3);
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);

    }

    public static CuratorFramework createClientByACLs(String connectionString, String user, String passwd) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(3000, 3);
        final String userPasswd = user + ":" + passwd;
        ACLProvider aclProvider = new ACLProvider() {
            private List<ACL> acls;

            @Override
            public List<ACL> getDefaultAcl() {
                if (acls == null) {
                    ArrayList<ACL> tmpACLs = ZooDefs.Ids.CREATOR_ALL_ACL;
                    tmpACLs.clear();
                    tmpACLs.add(new ACL(ZooDefs.Perms.ALL, new Id("auth", userPasswd)));
                    acls = tmpACLs;
                }
                return acls;
            }

            @Override
            public List<ACL> getAclForPath(String path) {
                return acls;
            }
        };
        String scheme = "digest";
        byte[] authBytes = userPasswd.getBytes();
        return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
                .aclProvider(aclProvider).authorization(scheme, authBytes).build();
    }

    public static CuratorFramework createClientWithOptions(String connectionString, int connectionTimeoutMs,
                                                           int sessionTimeoutMs, String user, String passwd) {
        final String userPasswd = user + ":" + passwd;
        ACLProvider aclProvider = new ACLProvider() {
            private List<ACL> acls;

            @Override
            public List<ACL> getDefaultAcl() {
                if (acls == null) {
                    ArrayList<ACL> tmpACLs = ZooDefs.Ids.CREATOR_ALL_ACL;
                    tmpACLs.clear();
                    tmpACLs.add(new ACL(ZooDefs.Perms.ALL, new Id("auth", userPasswd)));
                    acls = tmpACLs;
                }
                return acls;
            }

            @Override
            public List<ACL> getAclForPath(String path) {
                return acls;
            }
        };
        String scheme = "digest";
        byte[] authBytes = userPasswd.getBytes();
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(3000, 3);
        return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs).aclProvider(aclProvider).authorization(scheme, authBytes)
                .sessionTimeoutMs(sessionTimeoutMs).build();
    }

    public static CuratorFramework createClientWithOptions(String connectionString, int connectionTimeoutMs,
                                                           int sessionTimeoutMs) {
        // using the CuratorFrameworkFactory.builder() gives fine grained control
        // over creation options. See the CuratorFrameworkFactory.Builder javadoc
        // details
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(3000, 3);
        return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).build();
    }
}
