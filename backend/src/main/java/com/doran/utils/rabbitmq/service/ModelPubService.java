package com.doran.utils.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.doran.utils.rabbitmq.dto.req.ModelReqMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModelPubService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.model}")
    private String routingKey;

    //유저 하나당 큐 한개
    public void sendMessage(ModelReqMessage modelReqMessage) {
        log.info("모델 생성 요청 : " + modelReqMessage.getUserId());
        rabbitTemplate.convertAndSend(routingKey, modelReqMessage);
    }
}
