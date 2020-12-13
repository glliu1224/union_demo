package com.example.demo.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *@Description 连接zookeeper客户端
 *@Date 2020/12/13
 *@Author glliu
 */
@Slf4j
public class ZookeeperClientTest {

    private static final String ZK_ADDRESS = "192.168.72.129:2181";

    private static final int SESSION_TIMEOUT = 5000;

    private static ZooKeeper zooKeeper;

    private static String ZK_NODE = "/zk-node";

    /**
     *ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)
     * 在创建zookeeper客户端时，在此构造函数中每个参数的意义
     * 第一个参数：connectString，Zookeeper服务器列表，由英文逗号分开的host：port组成的，每一个代表一台机器。例如：host1:port1,host2:port2,host3:port3。
     *          另外，也可以在connectingString中设置客户端连接上Zookeeper后的根目录，例如host3:port3/zk-base
     * 第二个参数：sessionTimeout，会话的超时时间，是一个以毫秒为单位的整型值。在zookeeper中有会话的概念，在一个会话周期内，Zookeeper客户端和服务器之间海辉通过
     *          心跳检测机制维持会话的有效性，一旦在sessionTimeOut时间内没有进行有效的心跳检测，会话就会失效
     * 第三个参数：watcher，Zookeeper允许客户端在构造方法中传入一个接口watcher的实现类对象作为默认的watcher时间通知器，当然，改参数可以设置为null，标识不需要设置默认的监听器
     * 第四个参数：canBeReadOnly，一个Boolean类型参数，用于标识当前会话是否支持read-only，默认情况下，在Zookeeper集群中一个机器如果和集群中的一半或以上的机器失去联系
     *          那么这台机器不再处理客户端的请求，但是在某些场景下，当zookeeper发生故障的时候，我们还是希望此机器继续提供读的功能，这就是Zookeeper的read-only
     * 第五个参数：sessionId和 sessionPassWd，分别代表会话ID和会话密钥，这两个参数能够唯一确定一个会话，同时客户端使用这两个参数可以实现客户端会话复用，从而达到恢复会话的效果
     *          具体使用方法是，第一个连接上Zookeeper服务器时，通过调用Zookeeper对象实例的以下两个接口即可获得当前会话的会话ID和会话密钥，long getSession();byte[] getSessionPassWd()
     *          获取到这两个参数之后，那么就可以下次创建Zookeeper对象实例的时候传入构造方法了
     */

    @Before
    public void init() throws IOException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(ZK_ADDRESS, SESSION_TIMEOUT, event -> {
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected && event.getType() == Watcher.Event.EventType.None) {
                countDownLatch.countDown();
                log.info("连接成功");
            }
        });
        log.info("连接中");
        countDownLatch.await();
    }

    /**
     * 同步创建节点
     */
    @Test
    public void createTest() throws KeeperException, InterruptedException {
        String path = zooKeeper.create(ZK_NODE, "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        log.info("created path:{}", path);
    }

    /**
     * 异步创建节点
     */
    @Test
    public void createAyncTest() throws InterruptedException {
        zooKeeper.create(ZK_NODE,"data".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,(rc,path,ctx,name)->
            log.info("rc:{},path:{},ctx:{},name:{}", rc, path, ctx, name),"context");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

    }

    /**
     * 修改节点数据
     */
    @Test
    public void setTest() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData(ZK_NODE, false, stat);
        log.info("修改前的数据:{}", new String(data));
        zooKeeper.setData(ZK_NODE, "14:53".getBytes(), stat.getVersion());
        byte[] data1 = zooKeeper.getData(ZK_NODE, false, stat);
        log.info("修改后的数据:{}",new String(data1));
    }

}
