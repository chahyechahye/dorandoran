package com.doran.utils.rabbitmq.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.processed_voice.dto.res.PVQueResDto;
import com.doran.utils.common.Genders;
import com.doran.utils.rabbitmq.dto.res.VoiceResMessage;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class VoiceSubServiceTest {
    @Autowired
    VoiceSubService voiceSubService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.voice_res_test}")
    private String routingKey;

    @Test
    @DisplayName("보이스_SUB_생성기")
    public void 보이스_SUB_생성기() {
        VoiceResMessage voiceResMessage = new VoiceResMessage();
        List<PVQueResDto> pvList = new ArrayList<>();
        for (int i = 1016; i <= 1017; i++) {
            pvList.add(new PVQueResDto(i, "ttt"));
        }

        voiceResMessage.setUserId(17);
        voiceResMessage.setPvList(pvList);
        voiceResMessage.setGenders(Genders.MALE);

        log.info("큐 날라가욧_!!!!!!!!!!!!!!!!");
        rabbitTemplate.convertAndSend(routingKey, voiceResMessage);
    }

    @Test
    @DisplayName("낭독_여부_갱신_테스트")
    public void 낭독_여부_갱신_테스트() {
        voiceSubService.subscribeVoiceQue(new VoiceResMessage(17,Genders.MALE,null));
    }

}
