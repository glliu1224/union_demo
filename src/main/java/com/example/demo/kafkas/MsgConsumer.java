package com.example.demo.kafkas;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {

    @KafkaListener(topics = {"topic-1","topic-2"})
    public void processMessage(String content) {
        System.out.println("消息被消费" + content);
    }
}
