package com.example.demo.zookeeper.nodecache;

import com.example.demo.zookeeper.CuratorTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.junit.Test;

/**
 *@Description Curator监听器
 *@Date 2020/12/13
 *@Author glliu
 */
@Slf4j
public class NodeCacheTest extends CuratorStandaloneBase{

    public static final String NODE_CACHE = "/node-cache";

    /**
     * 对节点进行监听，一旦节点发生变化，nodeChanged方法就会执行
     * 此处使用curator框架对节点进行监听，每次当监听消费完成，都会自动对节点进行重新监听，所以当前节点每次发生变化都会被监听到
     * @throws Exception
     */
    @Test
    public void testNodeCacheTest() throws Exception {
        CuratorFramework curatorFramework = getCuratorFramework();
        createIfNeed(NODE_CACHE);
        NodeCache nodeCache = new NodeCache(curatorFramework, NODE_CACHE);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                log.info("{} Path Node Changed:",NODE_CACHE);
                printNodeData();
            }
        });
        nodeCache.start();
    }

    public void printNodeData() throws Exception {
        CuratorFramework curatorFramework = getCuratorFramework();
        byte[] bytes = curatorFramework.getData().forPath(NODE_CACHE);
        log.info("data:{}", new String(bytes));
    }
}
