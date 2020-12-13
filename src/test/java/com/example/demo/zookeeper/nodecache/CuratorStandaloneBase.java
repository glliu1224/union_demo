package com.example.demo.zookeeper.nodecache;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CuratorStandaloneBase {

    private static final String CONNECT_STR = "192.168.72.129:2181";
    private static final int SESSION_TIME_OUT = 60 * 1000;
    private static final int CONNECTION_TIME_OUT = 5000;
    private static CuratorFramework curatorFramework;

    /**
     * 前置方法，会在目标方法执行前调用，类似于AOP的前置方法
     */
    @Before
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 30);
        curatorFramework = CuratorFrameworkFactory.builder()//初始化curatorFramework
                .connectString(CONNECT_STR)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(SESSION_TIME_OUT)
                .connectionTimeoutMs(CONNECTION_TIME_OUT)
                .canBeReadOnly(true)
                .build();
        curatorFramework.getConnectionStateListenable().addListener((client,newState)->{
            if (newState == ConnectionState.CONNECTED) {
                log.info("连接成功");
            }
        });
        log.info("连接中");
        curatorFramework.start();
    }

    public void createIfNeed(String path) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (null == stat) {
            String s = curatorFramework.create().forPath(path);
            log.info("Path created:{}", s);
        }
    }

    public static CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    @After
    public void test() {
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String getConnectStr() {
        return CONNECT_STR;
    }
}
