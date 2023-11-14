package com.doran.utils.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.doran.redis.tel.service.TelService;
import com.doran.utils.rabbitmq.dto.req.VoiceReqMessage;
import com.doran.utils.rabbitmq.dto.res.ModelResMessage;
import com.doran.utils.sens.MessageType;
import com.doran.utils.sens.Naver_Sens_V2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModelSubService {
    private final Naver_Sens_V2 naverSensV2;
    private final TelService telService;
    private final VoicePubService voicePubService;

    @RabbitListener(queues = "model.res")
    public void subscribeModelQue(ModelResMessage modelResMessage) {
        // 목소리 생성완료 알림
        log.info("model_res 받았음!-----------------------------");
        telService.findByUserId(modelResMessage.getUserId()).ifPresent(
            tel -> {
                naverSensV2.send_msg(tel.getTel(), null, MessageType.MODEL);
            }
        );

        // 가공 목소리 생성 요청
        voicePubService.sendMessage(modelResMessage.getUserId(), modelResMessage.getGenders());
    }
}
