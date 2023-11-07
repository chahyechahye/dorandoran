package com.doran.utils.rabbitmq.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.service.RawVoiceService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.Genders;
import com.doran.utils.rabbitmq.dto.req.ModelReqMessage;
import com.doran.utils.rabbitmq.mapper.ModelMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModelPubService {
    private final RabbitTemplate rabbitTemplate;
    private final RawVoiceService rawVoiceService;
    private final ModelMapper modelMapper;

    @Value("${rabbitmq.queue.model}")
    private String routingKey;

    //유저 하나당 큐 한개
    public void sendMessage(int userId, Genders genders) {
        List<RawVoiceResDto> rawVoiceResDtoList = rawVoiceService.findRawVoiceByUserId(userId);
        ModelReqMessage modelReqMessage = modelMapper.toReqMessage(userId, rawVoiceResDtoList, genders);

        //log.info("입력값 확인" + modelReqMessage.getRawVoiceList().get(0).getVoiceUrl());
        log.info("모델 생성 요청 : " + modelReqMessage.getUserId());

        rabbitTemplate.convertAndSend(routingKey, modelReqMessage);
    }
}
