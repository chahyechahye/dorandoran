package com.doran.utils.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.doran.processed_voice.service.ProcessedVoiceService;
import com.doran.redis.record.mapper.RecordMapper;
import com.doran.redis.record.service.RecordService;
import com.doran.redis.tel.service.TelService;
import com.doran.utils.common.Genders;
import com.doran.utils.rabbitmq.dto.res.ModelResMessage;
import com.doran.utils.rabbitmq.dto.res.VoiceResMessage;
import com.doran.utils.sens.MessageType;
import com.doran.utils.sens.Naver_Sens_V2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoiceSubService {
    private final ProcessedVoiceService processedVoiceService;
    private final Naver_Sens_V2 naverSensV2;
    private final TelService telService;
    private final RecordService recordService;
    private final RecordMapper recordMapper;

    //@RabbitListener(queues = "voice.res")
    public void subscribeVoiceQue(VoiceResMessage voiceResMessage) {
        // 목소리 갱신
        processedVoiceService.saveAll(voiceResMessage);

        // 낭독 여부 갱신
        recordService.findById(String.valueOf(voiceResMessage.getUserId())).ifPresentOrElse(record -> {
            log.info("레디스 있어욧!!!!!!!!!!!!!!!!!");
            recordService.update(voiceResMessage.getUserId(),record, voiceResMessage.getGenders());
        }, () -> {
            log.info("레디스 없어욧!!!!!!!!!!!!!!!!!!!!!!!!");
            boolean maleAble = false;
            boolean femaleAble = false;

            if (voiceResMessage.getGenders().equals(Genders.MALE))
                maleAble = true;
            else
                femaleAble = true;
            recordService.save(recordMapper.toRecord(String.valueOf(voiceResMessage.getUserId()), maleAble, femaleAble));
        });

        // 목소리 생성완료 알림
        telService.findByUserId(voiceResMessage.getUserId()).ifPresent(tel -> {
                naverSensV2.send_msg(tel.getTel(), null, MessageType.VOICE);
            }
        );
    }
}
