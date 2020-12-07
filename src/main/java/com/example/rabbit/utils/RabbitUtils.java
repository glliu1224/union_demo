package com.example.rabbit.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取TCP连接工具类
 */
@Slf4j
public class RabbitUtils {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    static {
        connectionFactory.setHost("192.168.72.129");
        connectionFactory.setVirtualHost("glliu");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
    }
    public static Connection getConnection() {
        try {
            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (Exception e) {
            log.error("获取连接失败");
            throw new RuntimeException(e);
        }
    }
}
