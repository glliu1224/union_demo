package com.example.rabbit.direct;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_SMS, true, false, false, null);

        //向MQ发送数据
        for (int i = 0; i < 10; i++) {
            String message = "正在向您发送数据:" + i;
            channel.basicPublish("", RabbitConstant.QUEUE_SMS, null, message.getBytes());
        }
        System.out.println("消息发送成功");
        channel.close();
        connection.close();
    }
}
