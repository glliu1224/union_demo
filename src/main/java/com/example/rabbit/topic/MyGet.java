package com.example.rabbit.topic;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyGet {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.EXCHANGE_WEATHER_TOPIC, true, false, false, null);

        //绑定队列与交换机
        //第一个参数：队列   第二个参数：交换机   第三个参数：routingKey
        channel.queueBind(RabbitConstant.QUEUE_WEATHER_TOPIC, RabbitConstant.EXCHANGE_WEATHER_TOPIC, "china.#");

        //从MQ获取数据
        channel.basicConsume(RabbitConstant.QUEUE_WEATHER_TOPIC,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("消息消费成功:" + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
