package com.doran.utils.rabbitmq.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.service.RawVoiceService;
import com.doran.redis.model.key.Model;
import com.doran.redis.model.service.ModelService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.Genders;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;
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
    private final ModelService modelService;

    @Value("${rabbitmq.queue.model}")
    private String routingKey;

    public Boolean isMale(Genders genders) {
        return genders.equals(Genders.MALE);
    }

    public Boolean isFeMale(Genders genders) {
        return genders.equals(Genders.FEMALE);
    }

    //유저 하나당 큐 한개
    public void sendMessage(int userId, Genders genders) {
        List<RawVoiceResDto> rawVoiceResDtoList = rawVoiceService.findRawVoiceByUserId(userId, genders);
        ModelReqMessage modelReqMessage = modelMapper.toReqMessage(userId, rawVoiceResDtoList, genders);

        // 있으면? 성별 비교하고 모델 만든적 있으면 예외 던져버리기
        // 없으면? 레디스 새로 만들고 메시지 보내기,  model.able이 true면 생성이 가능함
        // 언젠가 리팩토링... isMale()과 isFeMale()중 하나만 있으면 성별 표현 가능,,
        modelService.findByUserId(userId).ifPresentOrElse(model -> {
            log.info("모델을 만든적이 있음");
            if ((isMale(genders) && !model.getMaleAble()) || (isFeMale(genders) && !model.getFemaleAble()))
                throw new CustomException(ErrorCode.DUPLICATE_MODEL);
            if (isMale(genders)) {
                model.setMaleAble(false);
                modelService.save(model);
            } else if (isFeMale(genders)) {
                model.setFemaleAble(false);
                modelService.save(model);
            }
        }, () -> modelService.save(
            new Model(String.valueOf(userId), !isMale(genders), !isFeMale(genders))));

        log.info("모델 생성 요청 : " + modelReqMessage.getUserId());

        //rabbitTemplate.convertAndSend(routingKey, modelReqMessage);
    }

}
