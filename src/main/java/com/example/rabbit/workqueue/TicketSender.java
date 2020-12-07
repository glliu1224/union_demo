package com.example.rabbit.workqueue;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.entity.Ticket;
import com.example.rabbit.utils.RabbitUtils;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class TicketSender {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_SMS, true, false, false, null);
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setPrice(BigDecimal.ZERO);
        ticket.setName("上海---哈尔滨");
        ticket.setCreateTime(new Date());
        ticket.setUpdateTime(new Date());
        String message = new Gson().toJson(ticket);
        channel.basicPublish("",RabbitConstant.QUEUE_SMS,null,message.getBytes());
        System.out.println("消息发送成功");
        channel.close();
        connection.close();
    }
}
