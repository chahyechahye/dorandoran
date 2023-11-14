package com.doran.utils.rabbitmq.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.utils.common.Genders;

@SpringBootTest
class ModelPubServiceTest {
    @Autowired
    private ModelPubService modelPubService;

    @Test
    void 모델_생성_메시지_테스트() {
        modelPubService.sendMessage(17, Genders.MALE);
    }

    @Test
    void 모델_중복_생성_체킹_테스트() {
        try {
            modelPubService.sendMessage(5, Genders.FEMALE);
        }catch (Exception e)
        {
            System.out.println("예외발생!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }
}
