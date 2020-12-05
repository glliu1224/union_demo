package com.example.rabbit.helloworld;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author glliu
 * @date 2020-12-05
 * @des:消息生产者
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取TCP长链接
        Connection connection = RabbitUtils.getConnection();
        //创建通信通道，相当于TCP长链接
        Channel channel = connection.createChannel();
        //创建队列，声明并创建一个队列，如果队列已经存在，就使用这个队列
        //第一个参数：队列名称ID
        //第二个参数：是否持久化，false：不持久化   MQ停掉，数据就会丢失
        //第三个参数：队列是否私有化   false代表所有消费者和都可以访问   true代表只有第一次拥有它的消费者才能一直使用，其它消费者不允许访问
        //第四个参数：是否自动删除      false代表连接停掉后不自动删除这个队列
        //其它额外参数   null
        channel.queueDeclare(RabbitConstant.QUEUE_HELLO_WORLD, true, false, false, null);

        String message = "今天吵架了";
        //四个参数
        //exchange  交换机，暂时用不到，进行发布订阅模式才能用到
        //队列名称
        //额外的设置属性
        //最后一个参数是要传递的消息字节数组
        channel.basicPublish("",RabbitConstant.QUEUE_HELLO_WORLD,null,message.getBytes());
        channel.close();
        connection.close();
        System.out.println("消息发送成功");
    }
}
