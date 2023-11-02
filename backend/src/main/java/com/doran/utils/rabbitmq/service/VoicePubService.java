package com.doran.utils.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doran.utils.rabbitmq.dto.req.VoiceReqMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoicePubService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.voice}")
    private String routingKey;

    public void sendMessage(VoiceReqMessage voiceReqMessage)
    {
        log.info("가공 목소리 생성 요청 : " + voiceReqMessage.getUserId());
        rabbitTemplate.convertAndSend(routingKey, voiceReqMessage);
    }
}
