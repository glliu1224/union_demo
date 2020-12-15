package com.example.demo.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *@Description  Curator是一个Zookeeper的客户端框架，把平时使用Zookeeper服务开发功能做了封装
 *              使用户使用Zookeeper的各种场景更加方便
 *              比如：Leader选举、分布式计数器、分布式锁、在会话重新连接，Watch反复注册、多种异常处理
 *@Date 2020/12/13
 *@Author glliu
 */
@Slf4j
public class CuratorTest {

    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

    /*项目中使用，是否可以将client作为Bean交给Spring管理?*/
    public CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, retryPolicy);

    private static final String ZK_ADDRESS = "192.168.72.129:2181";

    private static String ZK_NODE = "/zk-node";

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 进行客户端服务器交互，第一步创建会话
     * Curator提供了多种方式创建会话，
     * 第一种：静态工厂创建
     */
    @Before
    public void conversationCreate() {
        //重试策略
        client.start();
    }

    /**
     * 第二种：fluent风格创建
     */
    public void fluentCreate() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();
    }

    /**
     * 使用curator获取数据
     * @throws Exception
     * @result 成功访问，返回结果
     */
    @Test
    public void testGetData() throws Exception {
        byte[] bytes = client.getData().forPath(ZK_NODE);
        log.info("获取到的数据:{}",new String(bytes));
    }

    /**
     * 更新数据
     * @throws Exception
     * @result 成功访问，更新成功
     */
    @Test
    public void testUpdateData() throws Exception {
        //获取更新前的数据
        byte[] bytes1 = client.getData().forPath(ZK_NODE);
        log.info("更新前的数据:{}",new String(bytes1));
        //更新数据
        client.setData().forPath(ZK_NODE, "17:28".getBytes());
        byte[] bytes = client.getData().forPath(ZK_NODE);
        log.info("获取到更新后的数据:{}", new String(bytes));
    }

    /**
     * 删除节点
     * @throws Exception
     * @result 删除节点，成功执行
     * guaranteed ：该函数的功能如字面意思一样，主要起到一个保障删除成功的作用，其底层工作方式是：
     *              只要客户端会话有效，就会在后台持续发出删除请求知道该数据节点在Zookeeper服务端被删除
     * deletingChildrenIfNeeded：指定了此函数后，系统在删除该节点的时候，会以递归的方式删除该节点的所有
     *              子节点，及其子节点的子节点
     */
    @Test
    public void delData() throws Exception {
        String newPath = "/glliu-del";
        client.create().forPath(newPath, "glliu-del".getBytes());
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(newPath);
        try {
            byte[] bytes = client.getData().forPath(newPath);
        } catch (Exception e) {
            log.warn("节点已经删除");
        }
    }


    /**
     * 自己写的删除节点方法
     * @throws Exception
     */
    @Test
    public void testDeleteData() throws Exception {
        String newPath = "/glliu-del";
        String s = client.create().forPath(newPath, "glliu-del".getBytes());
        log.info("创建节点返回的数据:{}", s);
        byte[] bytes = client.getData().forPath(newPath);
        log.info("获取到新节点数据:{}", new String(bytes));

        //此处源码待观摩，待研究
        client.delete().forPath(newPath);
        try {
            byte[] bytes1 = client.getData().forPath(newPath);
        } catch (Exception e) {
            log.warn("节点已删除");
        }
    }

    /**
     * Curator中引入了BackgroundCallback接口，用来处理服务器端返回来的信息，处理过程是在异步线程中调用，默认在
     * EventThread中调用，也可以自定义线程池
     */
    @Test
    public void test() throws Exception {
        client.getData().inBackground((first, second) -> {
            log.info("backGround:{}", second);
        }).forPath(ZK_NODE);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    /**
     * 此方法待重新看
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        client.getData().inBackground((first, second) -> {
            log.info("backGround:{}", second);
        },executorService).forPath(ZK_NODE);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    /**
     * Curator监听器
     */
}
