/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package distribute;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;


@SuppressWarnings("unchecked")
public class ZaCuratorClient {
    private final static Logger logger       = LoggerFactory.getLogger(ZaCuratorClient.class);
    @SuppressWarnings("rawtypes")
	private HashMap<CuratorCachePair, NodeCache> nodeCacheMap = new HashMap<CuratorCachePair, NodeCache>();
	@SuppressWarnings("rawtypes")
	private HashMap<CuratorCachePair, TreeCache> treeCacheMap = new HashMap<CuratorCachePair, TreeCache>();
    private CuratorFramework client;
    private volatile boolean                     isStarted    = false;

    public ZaCuratorClient(String conString, String user, String passwd) {
        client = ZaCuratorFactory.createClientByACLs(conString, user, passwd);
    }

    public ZaCuratorClient(String conString) {
        client = ZaCuratorFactory.createClient(conString);
    }

    public ZaCuratorClient(String conString, int connectionTimeoutMs, int sessionTimeoutMs) {
        client = ZaCuratorFactory.createClientWithOptions(conString, connectionTimeoutMs, sessionTimeoutMs);
    }

    public ZaCuratorClient(String conString, int connectionTimeoutMs, int sessionTimeoutMs, String user, String passwd) {
        client = ZaCuratorFactory.createClientWithOptions(conString, connectionTimeoutMs, sessionTimeoutMs, user,
                passwd);
    }

    public void registerSessionListener(ConnectionStateListener listener) {
        //logger.info("",client.ge);
        client.getConnectionStateListenable().addListener(listener);
    }

    public boolean isExists(String path) {
        try {
            Stat pathStat = client.checkExists().forPath(path);
            if (pathStat != null) {
                return true;
            }
        } catch (Exception e) {
            logger.warn("pathExists [{}] throw a Exception.", path);
        }
        return false;
    }

    public void createPath(String path, byte[] payload) throws Exception {
        client.create().creatingParentsIfNeeded().forPath(path, payload);
    }

    public void createEphemeral(String path, byte[] payload) throws Exception {
        // this will create the given EPHEMERAL ZNode with the given data
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
    }

    public String createEphemeralSequential(String path, byte[] payload) throws Exception {
        // this will create the given EPHEMERAL-SEQUENTIAL ZNode with the given data using Curator protection.
        return client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
    }

    public void setData(String path, byte[] payload) throws Exception {
        // set data for the given node
        client.setData().forPath(path, payload);
    }

    public void setDataAsync(String path, byte[] payload) throws Exception {
        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
            }
        };
        client.getCuratorListenable().addListener(listener);
        client.setData().inBackground().forPath(path, payload);
    }

    public void setDataAsyncWithCallback(BackgroundCallback callback, String path, byte[] payload) throws Exception {
        client.setData().inBackground(callback).forPath(path, payload);
    }

    public void delete(String path) throws Exception {
        client.delete().forPath(path);
    }

    public void guaranteedDelete(String path) throws Exception {
        client.delete().guaranteed().forPath(path);
    }

    public List<String> getChildren(String path) throws Exception {
        return client.getChildren().forPath(path);
    }


	@SuppressWarnings("rawtypes")
	public synchronized TreeCache registerTreeWatcher(String path, final ZaTreeWatcher watcher) throws Exception {
        CuratorCachePair tmp = new CuratorCachePair(client, path);
        TreeCache tmpTreeCache = null;
        if (treeCacheMap.containsKey(tmp)) {
            tmpTreeCache = treeCacheMap.get(tmp);
        } else {
            tmpTreeCache = new TreeCache(client, path);
            tmpTreeCache.start();
            treeCacheMap.put(tmp, tmpTreeCache);
        }
        final TreeCache treeCache = tmpTreeCache;

        TreeCacheListener listener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                if (TreeCacheEvent.Type.INITIALIZED.equals(treeCacheEvent.getType())) {
                    return;
                }
                try {
                    watcher.process(treeCacheEvent.getType(), treeCacheEvent.getData().getData(), treeCacheEvent.getData()
                            .getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        treeCache.getListenable().addListener(listener);
        return treeCache;
    }

    @SuppressWarnings("rawtypes")
	public synchronized NodeCache registerNodeWatcher(String path, final ZaNodeWatcher watcher) throws Exception {
        CuratorCachePair tmp = new CuratorCachePair(client, path);
        NodeCache tmpNodeCache = null;
        if (nodeCacheMap.containsKey(tmp)) {
            tmpNodeCache = nodeCacheMap.get(tmp);
        } else {
            tmpNodeCache = new NodeCache(client, path);
            tmpNodeCache.start(true);
            nodeCacheMap.put(tmp, tmpNodeCache);
        }
        final NodeCache nodeCache = tmpNodeCache;
        NodeCacheListener listener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                watcher.process(nodeCache.getCurrentData().getData());
            }
        };
        nodeCache.getListenable().addListener(listener);
        return nodeCache;
    }

    public byte[] getData(String path) throws Exception {
        return client.getData().forPath(path);
    }

    public synchronized void close() {
        if (isStarted) {
            nodeCacheMap.clear();
            treeCacheMap.clear();
            nodeCacheMap = null;
            treeCacheMap = null;

            client.close();
            client = null;
            isStarted = false;
        }
    }

    public synchronized void start() throws Exception {
        if (isStarted) {
            return;
        } else if (client != null) {
            client.start();
            isStarted = true;
            return;
        } else {
            throw new ZaCuratorException("ZaCuratorClient has been closed.");
        }
    }

    public interface ZaNodeWatcher {
        void process(byte[] data) throws Exception;
    }

    public interface ZaTreeWatcher {
        void process(TreeCacheEvent.Type type, byte[] data, String childPath) throws Exception;
    }

    private class CuratorCachePair<T, M> {
        private final T one;
        private final M two;

        public CuratorCachePair(T one, M two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            CuratorCachePair<?, ?> that = (CuratorCachePair<?, ?>) o;

            if (one != null ? !one.equals(that.one) : that.one != null)
                return false;
            return two != null ? two.equals(that.two) : that.two == null;

        }

        @Override
        public int hashCode() {
            int result = one != null ? one.hashCode() : 0;
            result = 31 * result + (two != null ? two.hashCode() : 0);
            return result;
        }
    }
}
