package com.doran.utils.rabbitmq.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModelPubServiceTest {
    @Autowired
    private ModelPubService modelPubService;

    @Test
    void 모델_생성_메시지_테스트() {
        modelPubService.sendMessage(3);
    }
}