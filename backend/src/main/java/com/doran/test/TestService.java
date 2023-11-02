package com.doran.test;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.hong}")
    private String routingKey;

    public void sendMessage(TestMessage testMessage) {
        log.info("Sent Msg : {}", testMessage);
        rabbitTemplate.convertAndSend(routingKey, testMessage);
    }

    @RabbitListener(queues = "hong.req")
    public void sub(TestMessage testMessage) throws InterruptedException {
        Thread.sleep(3000);
        log.info("ㅎㅇㅎㅇ");
        log.info("메시지 : " + testMessage.getText());
        log.info("sub : " + testMessage.getSub());
    }
}
