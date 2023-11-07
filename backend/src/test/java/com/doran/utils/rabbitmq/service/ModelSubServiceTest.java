package com.doran.utils.rabbitmq.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.utils.rabbitmq.dto.res.ModelResMessage;

@SpringBootTest
class ModelSubServiceTest {
    @Autowired
    ModelSubService modelSubService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.model_res_test}")
    private String routingKey;

    @Test
    public void 모델_SUB_생성기() {
        rabbitTemplate.convertAndSend(routingKey, new ModelResMessage(17));
    }

}