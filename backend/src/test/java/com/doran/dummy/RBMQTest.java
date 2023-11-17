package com.doran.dummy;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RBMQTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        RabbitAdmin ra = new RabbitAdmin(rabbitTemplate);
        Properties queueProperties = ra.getQueueProperties("model.req");

        System.out.println(queueProperties);
        System.out.println(queueProperties.toString());
        System.out.println(queueProperties.get("QUEUE_MESSAGE_COUNT"));
        System.out.println(queueProperties.get("QUEUE_CONSUMER_COUNT"));
    }
}
