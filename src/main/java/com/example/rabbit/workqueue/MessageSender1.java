package com.example.rabbit.workqueue;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class MessageSender1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_HELLO_WORLD, true, false, false, null);
        channel.basicQos(1);//处理完一个再取一个

        channel.basicConsume(RabbitConstant.QUEUE_HELLO_WORLD, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String jsonSMS = new String(body);
                System.out.println("消息消费成功:" + jsonSMS);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
