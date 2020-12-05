package com.example.rabbit.workqueue;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.utils.RabbitUtils;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author :glliu
 * @date 2020-10-05
 * @des 买车票发送消息
 */
public class OrderProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_SMS, true, false, false, null);
        for (int i = 0; i < 10; i++) {
            //买车票
            Message message = new Message("尊敬的乘客" + i + "您好,您的车票已经预定成功");
            String msg = new Gson().toJson(message);
            channel.basicPublish("",RabbitConstant.QUEUE_HELLO_WORLD,null,msg.getBytes());
        }
        System.out.println("发送消息成功");
        channel.close();
        connection.close();
    }
}
