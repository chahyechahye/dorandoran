package com.doran.utils.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.doran.utils.rabbitmq.dto.res.ModelResMessage;
import com.doran.utils.sens.Naver_Sens_V2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModelSubService {

    @RabbitListener(queues = "model.res")
    public void subscribeModelQue(ModelResMessage modelResMessage)
    {
        // 목소리 생성완료 알림

        // 가공 목소리 생성 요청
    }
}
