package com.example.rabbit.helloworld;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * glliu
 * @date 2020-12-04
 * @desc rabbit入门篇
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        //获取TCP长链接
        Connection connection = RabbitUtils.getConnection();
        //创建通信通道
        Channel channel = connection.createChannel();
        //创建队列，声明并创建队列，如果队列已经存在，使用这个队列
        //第一个参数:队列名称ID
        //第二个参数：是否持久化 false代表不持久化，MQ停掉这个队列就会丢失
        //第三个参数：是否私有化 false代表所有的队列都可以访问，如果是true，代表第一个拥有这个队列的消费者可以一直使用，其它消费者不允许使用
        //第四个参数：是否自动删除，false代表连接停掉后不自动删除这个队列
        //其它额外参数 null
        channel.queueDeclare(RabbitConstant.QUEUE_HELLO_WORLD, true, false, false,null);

        //从MQ服务器中获取数据
        channel.basicConsume(RabbitConstant.QUEUE_HELLO_WORLD, false, new Reciver(channel));
    }
}

class Reciver extends DefaultConsumer {

    private Channel channel;

    //重写构造方法,channel通道对象需要从外层传入，在handleDelivery中需要使用
    public Reciver(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body);
        System.out.println("消费者接收到消息:" + message);
        System.out.println("消息的TagId:" + envelope.getDeliveryTag());
        //false只确认签收当前消息，设置为true时则代表签收该消费者当前未签收的所有消息
        channel.basicAck(envelope.getDeliveryTag(),false);
    }
}
