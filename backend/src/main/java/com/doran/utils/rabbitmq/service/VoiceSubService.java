package com.doran.utils.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.doran.utils.rabbitmq.dto.res.ModelResMessage;
import com.doran.utils.rabbitmq.dto.res.VoiceResMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoiceSubService {

    @RabbitListener(queues = "model.res")
    public void subscribeVoiceQue(VoiceResMessage voiceResMessage)
    {
        // 목소리 갱신

        // 목소리 생성완료 알림
    }
}
