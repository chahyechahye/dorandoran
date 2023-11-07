package com.doran.utils.rabbitmq.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doran.admin_voice.dto.res.AdminFindResDto;
import com.doran.admin_voice.service.AdminVoiceService;
import com.doran.utils.common.Genders;
import com.doran.utils.rabbitmq.dto.req.VoiceReqMessage;
import com.doran.utils.rabbitmq.mapper.VoiceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoicePubService {
    private final RabbitTemplate rabbitTemplate;
    private final AdminVoiceService adminVoiceService;
    private final VoiceMapper voiceMapper;

    @Value("${rabbitmq.queue.voice}")
    private String routingKey;

    public void sendMessage(int userId, Genders genders) {
        List<AdminFindResDto> list = adminVoiceService.findAdminVoiceAndBook(genders);
        VoiceReqMessage voiceReqMessage = voiceMapper.toReqMessage(userId, list, genders);

        log.info("가공 목소리 생성 요청 : " + voiceReqMessage.getUserId());
        rabbitTemplate.convertAndSend(routingKey, voiceReqMessage);
    }
}
